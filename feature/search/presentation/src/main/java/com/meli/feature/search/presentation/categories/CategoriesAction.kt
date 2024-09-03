package com.meli.feature.search.presentation.categories

sealed class CategoriesAction {
    data class NavigateToProductList(val categorieId: String) :  CategoriesAction()
}