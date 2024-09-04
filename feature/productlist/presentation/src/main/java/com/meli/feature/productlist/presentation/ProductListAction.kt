package com.meli.feature.productlist.presentation

sealed class ProductListAction {
    data class NavigateToProductDetail(val id: String) : ProductListAction()
}