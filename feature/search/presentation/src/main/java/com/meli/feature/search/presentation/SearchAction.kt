package com.meli.feature.search.presentation

sealed class SearchAction {
    data class NavigateToProductList(val product: String): SearchAction()
}