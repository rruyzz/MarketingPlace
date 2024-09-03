package com.meli.feature.productlist.presentation

import com.meli.feature.productlist.domain.model.ProductModel

data class ProductState(
    val isLoading: Boolean = false,
    val productList: List<ProductModel> = listOf(),
    val errorMessage: String = String(),
)