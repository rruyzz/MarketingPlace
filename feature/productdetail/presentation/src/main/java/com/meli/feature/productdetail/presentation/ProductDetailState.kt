package com.meli.feature.productdetail.presentation

import com.meli.feature.productdetail.domain.model.ProductDetail

data class ProductDetailState(
    val isLoading: Boolean = false,
    val error: String = String(),
    val data: ProductDetail? = null,
)