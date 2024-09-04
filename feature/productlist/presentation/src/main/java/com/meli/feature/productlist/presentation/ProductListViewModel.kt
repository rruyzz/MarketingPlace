package com.meli.feature.productlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.provider.ProductListProvider
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
    private val productListProvider: ProductListProvider
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState = _productListState.asStateFlow()
    private val _productListAction = MutableSharedFlow<ProductListAction>(0)
    val productListAction = _productListAction.asSharedFlow()

    init {
        getProducts()
    }

    fun onProductClick(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _productListAction.emit(ProductListAction.NavigateToProductDetail(id))
    }

    private fun getProducts() = viewModelScope.launch(Dispatchers.IO) {
        productListProvider(query, isCategory)
            .flowOn(Dispatchers.IO)
            .onStart { emitLoading(isLoading = true) }
            .onCompletion { emitLoading(isLoading = false) }
            .catch { _productListState.emit(ProductListState(errorMessage = it.message.orEmpty())) }
            .collect(::handleSuccess)
    }

    private fun handleSuccess(categoriesList: List<ProductModel>?) {
        emitProductList(productList = categoriesList)
    }

    private fun emitLoading(isLoading: Boolean) = viewModelScope.launch {
        _productListState.update { currentState ->
            currentState.copy(isLoading = isLoading)
        }
    }

    private fun emitProductList(productList: List<ProductModel>?) = viewModelScope.launch {
        _productListState.update { currentState ->
            currentState.copy(productList = productList)
        }
    }
}