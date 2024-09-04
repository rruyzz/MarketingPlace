package com.meli.feature.productdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productdetail.domain.provider.ProductDetailProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val id: String,
    private val providerDetail: ProductDetailProvider,
) : ViewModel() {
    private val _categoriesDetailState = MutableStateFlow(ProductDetailState())
    val categoriesDetailState = _categoriesDetailState.asStateFlow()

    init {
        getProductDetail()
    }

    private fun getProductDetail() = viewModelScope.launch(Dispatchers.IO) {
        providerDetail(id)
            .flowOn(Dispatchers.IO)
            .onStart { _categoriesDetailState.emit(ProductDetailState(isLoading = true)) }
            .onCompletion {}
            .catch { _categoriesDetailState.emit(ProductDetailState(error = it.message.orEmpty())) }
            .collect { _categoriesDetailState.emit(ProductDetailState(data = it)) }
    }
}