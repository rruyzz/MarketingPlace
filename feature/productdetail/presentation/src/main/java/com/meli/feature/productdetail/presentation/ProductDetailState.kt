package com.meli.feature.productdetail.presentation

import com.meli.feature.productdetail.domain.model.ProductDetailModel

data class ProductDetailState(
    val isLoading: Boolean = false,
    val error: String = String(),
    val productDetail: ProductDetailModel? = null,
)