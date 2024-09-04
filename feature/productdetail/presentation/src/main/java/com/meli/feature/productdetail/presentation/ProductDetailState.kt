package com.meli.feature.productdetail.presentation

data class ProductDetailState(
    val isLoading: Boolean = false,
    val error: String = String(),
    val data: String = String(),
)