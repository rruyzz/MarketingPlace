package com.meli.feature.productdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val query: String,
) : ViewModel() {
    private val _categoriesDetailState = MutableSharedFlow<ProductDetailState>(0)
    val categoriesDetailState = _categoriesDetailState.asSharedFlow()

    init {
        getProductDetail()
    }

    fun getProductDetail() = viewModelScope.launch(Dispatchers.IO) {
        _categoriesDetailState.emit(ProductDetailState(isLoading = true))
        delay(5000)
        _categoriesDetailState.emit(ProductDetailState(isLoading = false))
        _categoriesDetailState.emit(ProductDetailState(data = query))
    }
}