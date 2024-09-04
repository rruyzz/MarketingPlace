package com.meli.feature.search.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.search.domain.model.CategoriesModel
import com.meli.feature.search.domain.usecase.CategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategoriesUseCase: CategoriesUseCase
) : ViewModel() {

    private val _categoriesState = MutableStateFlow(CategoriesState())
    val categoriesState = _categoriesState.asStateFlow()
    private val _categoryAction = MutableSharedFlow<CategoriesAction>(0)
    val categoryAction = _categoryAction.asSharedFlow()

    init {
        getCategories()
    }

    fun onCategoryClick(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _categoryAction.emit(CategoriesAction.NavigateToProductList(categorieId = id))
    }

    private fun getCategories() = viewModelScope.launch(Dispatchers.IO) {
        getCategoriesUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { emitLoading(true) }
            .onCompletion { emitLoading(false) }
            .catch { _categoriesState.emit(CategoriesState(errorMessage = it.message.orEmpty())) }
            .collect(::handleSuccess)
    }

    private fun handleSuccess(categoriesList: List<CategoriesModel>?) {
        emitCategoriesList(categoriesList = categoriesList)
    }

    private fun emitLoading(isLoading: Boolean) = viewModelScope.launch {
        _categoriesState.update { currentState ->
            currentState.copy(isLoading = isLoading)
        }
    }

    private fun emitCategoriesList(categoriesList: List<CategoriesModel>?) = viewModelScope.launch {
        _categoriesState.update { currentState ->
            currentState.copy(categoriesList = categoriesList)
        }
    }
}