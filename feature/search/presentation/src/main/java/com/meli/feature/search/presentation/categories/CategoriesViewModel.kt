package com.meli.feature.search.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.search.domain.usecase.CategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategoriesUseCase: CategoriesUseCase
): ViewModel() {

    private val _categoriesState = MutableSharedFlow<CategoriesState>(0)
    val categoriesState = _categoriesState.asSharedFlow()
    private val _categoryAction = MutableSharedFlow<CategoriesAction>(0)
    val categoryAction = _categoryAction.asSharedFlow()

    fun getCategories() = viewModelScope.launch(Dispatchers.IO) {
        getCategoriesUseCase()
            .flowOn(Dispatchers.IO)
            .onStart {
                _categoriesState.emit(CategoriesState(isLoading = true))
            }
            .onCompletion {
            }
            .catch {
                _categoriesState.emit(CategoriesState(errorMessage = it.message.orEmpty()))
            }
            .collect {
                _categoriesState.emit(CategoriesState(categoriesList = it))
            }
    }

    fun onCategoryClick(id: String) = viewModelScope.launch(Dispatchers.IO){
        _categoryAction.emit(CategoriesAction.NavigateToProductList(categorieId = id))
    }
}