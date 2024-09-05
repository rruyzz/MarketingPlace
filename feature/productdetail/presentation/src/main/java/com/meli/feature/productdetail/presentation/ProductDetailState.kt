package com.meli.feature.productdetail.presentation

import com.meli.feature.productdetail.domain.model.ProductDetailModel

data class ProductDetailState(
    val isLoading: Boolean = false,
    val productDetail: ProductDetailModel? = null,
    val throwable: Throwable? = null,
)