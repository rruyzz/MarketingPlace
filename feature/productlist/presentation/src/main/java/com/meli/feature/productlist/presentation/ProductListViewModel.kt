package com.meli.feature.productlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.provider.ProductListProvider
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

class ProductListViewModel(
    private val query: String,
    private val isCategory: Boolean,
    private val productListProvider: ProductListProvider,
    private val dispatchers: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState = _productListState.asStateFlow()
    private val _productListAction = MutableSharedFlow<ProductListAction>(0)
    val productListAction = _productListAction.asSharedFlow()

    init {
        getProducts()
    }

    fun onProductClick(id: String) = viewModelScope.launch(dispatchers) {
        _productListAction.emit(ProductListAction.NavigateToProductDetail(id))
    }

    fun onToolbarClick() {
        emitNavigateBack()
    }

    fun onTryAgainDialogClick() {
        cleanThrowable()
        getProducts()
    }

    fun onCancelDialogClick() {
        emitNavigateBack()
    }

    private fun getProducts() = viewModelScope.launch(dispatchers) {
        productListProvider(query, isCategory)
            .onStart { emitLoading(isLoading = true) }
            .onCompletion { emitLoading(isLoading = false) }
            .catch { handleError(it) }
            .collect(::handleSuccess)
    }

    private fun handleSuccess(categoriesList: List<ProductModel>?) {
        emitProductList(productList = categoriesList)
    }

    private fun handleError(throwable: Throwable) {
        emitThrowable(throwable)
    }

    private fun emitLoading(isLoading: Boolean) = viewModelScope.launch(dispatchers) {
        _productListState.update { currentState ->
            currentState.copy(isLoading = isLoading)
        }
    }

    private fun emitProductList(productList: List<ProductModel>?) = viewModelScope.launch(dispatchers){
        _productListState.update { currentState ->
            currentState.copy(productList = productList)
        }
    }

    private fun emitNavigateBack() = viewModelScope.launch(dispatchers) {
        _productListAction.emit(ProductListAction.OnBackPressed)
    }

    private fun emitThrowable(throwable: Throwable) = viewModelScope.launch(dispatchers) {
        _productListState.update { currentState ->
            currentState.copy(throwable = throwable)
        }
    }

    private fun cleanThrowable() = viewModelScope.launch(dispatchers) {
        _productListState.update { currentState ->
            currentState.copy(throwable = null)
        }
    }
}