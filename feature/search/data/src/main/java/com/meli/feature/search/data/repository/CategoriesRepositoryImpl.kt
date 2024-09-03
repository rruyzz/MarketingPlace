package com.meli.feature.search.data.repository

import com.meli.core.network.domain.category.usecase.GetCategoriesUseCase
import com.meli.feature.search.data.mapper.toDomain
import com.meli.feature.search.domain.model.CategoriesModel
import com.meli.feature.search.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl(
    private val getCategoriesUseCase: GetCategoriesUseCase
): CategoriesRepository {

    override suspend fun getCategories(): List<CategoriesModel> {
        return getCategoriesUseCase().toDomain()
    }
}