package com.meli.feature.search.presentation.categories

import com.meli.feature.search.domain.model.CategoriesModel

data class CategoriesState(
    val isLoading: Boolean = false,
    val categoriesList: List<CategoriesModel>? = null,
    val errorMessage: String? = null,
)