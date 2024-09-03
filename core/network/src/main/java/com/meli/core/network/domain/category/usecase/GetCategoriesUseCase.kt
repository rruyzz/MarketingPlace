package com.meli.core.network.domain.category.usecase

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.domain.category.mapper.toDomain
import com.meli.core.network.domain.category.model.CategoriesDto

class GetCategoriesUseCase(
    private val dataSource: MarketingPlaceDataSource
) {

    suspend operator fun invoke() : List<CategoriesDto> {
        return dataSource.getCategories().toDomain()
    }
}