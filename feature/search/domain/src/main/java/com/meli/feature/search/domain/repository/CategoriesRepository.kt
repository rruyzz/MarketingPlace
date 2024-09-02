package com.meli.feature.search.domain.repository

import com.meli.feature.search.domain.model.CategoriesModel

interface CategoriesRepository {

    suspend fun getCategories(): List<CategoriesModel>
}