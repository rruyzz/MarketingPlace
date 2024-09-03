package com.meli.feature.productlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productlist.domain.usecase.ProductListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val query: String?,
    private val isCategory: Boolean?,
    private val productListUseCase: ProductListUseCase
) : ViewModel() {

    private val _categoriesState = MutableSharedFlow<ProductState>(0)
    val categoriesState = _categoriesState.asSharedFlow()

    init {
        getProducts()
    }
    fun getProducts() = viewModelScope.launch(Dispatchers.IO) {
        productListUseCase(query.orEmpty())
            .flowOn(Dispatchers.IO)
            .onStart {
                _categoriesState.emit(ProductState(isLoading = true))
            }
            .onCompletion {
            }
            .catch {
                _categoriesState.emit(ProductState(errorMessage = it.message.orEmpty()))
            }
            .collect {
                println(it.toString())
                _categoriesState.emit(ProductState(productList = it))
            }
    }

}