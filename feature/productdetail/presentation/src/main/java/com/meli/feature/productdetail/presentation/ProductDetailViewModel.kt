package com.meli.feature.productdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productdetail.domain.model.ProductDetailModel
import com.meli.feature.productdetail.domain.provider.ProductDetailProvider
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
    private val providerDetail: ProductDetailProvider,
) : ViewModel() {
    private val _categoriesDetailState = MutableSharedFlow<ProductDetailState>()
    val categoriesDetailState = _categoriesDetailState.asSharedFlow()

    fun getProductDetail() = viewModelScope.launch(Dispatchers.IO) {
        providerDetail(query)
            .flowOn(Dispatchers.IO)
            .onStart { _categoriesDetailState.emit(ProductDetailState(isLoading = true)) }
            .onCompletion {}
            .catch { _categoriesDetailState.emit(ProductDetailState(error = it.message.orEmpty())) }
            .collect { _categoriesDetailState.emit(ProductDetailState(data = it)) }
    }
}