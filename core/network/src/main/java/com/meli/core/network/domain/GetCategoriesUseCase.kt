package com.meli.core.network.domain

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.domain.model.CategoriesDto

class GetCategoriesUseCase(
    private val dataSource: MarketingPlaceDataSource
) {

    suspend operator fun invoke() : List<CategoriesDto> {
        return dataSource.getCategories().toDomain()
    }
}