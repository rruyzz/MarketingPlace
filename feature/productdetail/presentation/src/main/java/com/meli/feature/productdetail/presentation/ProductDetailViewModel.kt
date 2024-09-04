package com.meli.feature.productdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productdetail.domain.model.ProductDetailModel
import com.meli.feature.productdetail.domain.provider.ProductDetailProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val id: String,
    private val providerDetail: ProductDetailProvider,
) : ViewModel() {

    private val _productDetailState = MutableStateFlow(ProductDetailState())
    val productDetailState = _productDetailState.asStateFlow()

    private val _productDetailAction = MutableSharedFlow<ProductDetailAction>(0)
    val productDetailAction = _productDetailAction.asSharedFlow()

    init {
        getProductDetail()
    }

    fun onToolbarClick() {
        emitNavigateBack()
    }

    private fun getProductDetail() = viewModelScope.launch(Dispatchers.IO) {
        providerDetail(id)
            .onStart { emitLoading(isLoading = true) }
            .onCompletion { emitLoading(isLoading = false) }
            .catch { _productDetailState.emit(ProductDetailState(error = it.message.orEmpty())) }
            .collect(::handleSuccess)
    }

    private fun handleSuccess(productDetail: ProductDetailModel) {
        emitProductDetail(productDetail = productDetail)
    }

    private fun emitLoading(isLoading: Boolean) = viewModelScope.launch {
        _productDetailState.update { currentState ->
            currentState.copy(isLoading = isLoading)
        }
    }

    private fun emitProductDetail(productDetail: ProductDetailModel) = viewModelScope.launch {
        _productDetailState.update { currentState ->
            currentState.copy(productDetail = productDetail)
        }
    }

    private fun emitNavigateBack() = viewModelScope.launch {
        _productDetailAction.emit(ProductDetailAction.OnBackPressed)
    }
}