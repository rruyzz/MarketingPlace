package com.meli.feature.productlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productlist.domain.provider.ProductListProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val query: String,
    private val isCategory: Boolean,
    private val productListProvider: ProductListProvider
) : ViewModel() {

    private val _categoriesState = MutableSharedFlow<ProductListState>(0)
    val categoriesState = _categoriesState.asSharedFlow()
    private val _categoriesAction = MutableSharedFlow<ProductListAction>(0)
    val categoriesAction = _categoriesAction.asSharedFlow()

    init {
        getProducts()
    }
    fun getProducts() = viewModelScope.launch(Dispatchers.IO) {
        productListProvider(query, isCategory)
            .flowOn(Dispatchers.IO)
            .onStart {
                _categoriesState.emit(ProductListState(isLoading = true))
            }
            .onCompletion {
            }
            .catch {
                _categoriesState.emit(ProductListState(errorMessage = it.message.orEmpty()))
            }
            .collect {
                println(it.toString())
                _categoriesState.emit(ProductListState(productList = it))
            }
    }

    fun onProductClick(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _categoriesAction.emit(ProductListAction.NavigateToProductDetail(id))
    }

}