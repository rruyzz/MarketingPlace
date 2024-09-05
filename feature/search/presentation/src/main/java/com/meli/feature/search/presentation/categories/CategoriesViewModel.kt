package com.meli.feature.search.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.search.domain.model.CategoriesModel
import com.meli.feature.search.domain.usecase.CategoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
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
    private val getCategoriesUseCase: CategoriesUseCase,
    private val dispatchers: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val _categoriesState = MutableStateFlow(CategoriesState())
    val categoriesState = _categoriesState.asStateFlow()
    private val _categoryAction = MutableSharedFlow<CategoriesAction>(0)
    val categoryAction = _categoryAction.asSharedFlow()

    init {
        getCategories()
    }

    fun onTryAgainDialogClick() {
        cleanThrowable()
        getCategories()
    }

    fun onCancelDialogClick() {
        emitOnBackAction()
    }

    fun onCategoryClick(id: String) = viewModelScope.launch(dispatchers) {
        _categoryAction.emit(CategoriesAction.NavigateToProductList(categorieId = id))
    }

    private fun getCategories() = viewModelScope.launch() {
        getCategoriesUseCase()
            .flowOn(dispatchers)
            .onStart { emitLoading(true) }
            .onCompletion { emitLoading(false) }
            .catch { handleError(it) }
            .collect(::handleSuccess)
    }

    private fun handleSuccess(categoriesList: List<CategoriesModel>?) {
        emitCategoriesList(categoriesList = categoriesList)
    }

    private fun handleError(throwable: Throwable) {
        emitThrowable(throwable)
    }

    private fun emitLoading(isLoading: Boolean) = viewModelScope.launch(dispatchers) {
        _categoriesState.update { currentState ->
            currentState.copy(isLoading = isLoading)
        }
    }

    private fun emitCategoriesList(categoriesList: List<CategoriesModel>?) =
        viewModelScope.launch(dispatchers) {
            _categoriesState.update { currentState ->
                currentState.copy(categoriesList = categoriesList)
            }
        }

    private fun emitThrowable(throwable: Throwable) = viewModelScope.launch(dispatchers) {
        _categoriesState.update { currentState ->
            currentState.copy(throwable = throwable)
        }
    }

    private fun cleanThrowable() = viewModelScope.launch(dispatchers) {
        _categoriesState.update { currentState ->
            currentState.copy(throwable = null)
        }
    }

    private fun emitOnBackAction() = viewModelScope.launch(dispatchers) {
        _categoryAction.emit(CategoriesAction.OnBackAction)
    }
}