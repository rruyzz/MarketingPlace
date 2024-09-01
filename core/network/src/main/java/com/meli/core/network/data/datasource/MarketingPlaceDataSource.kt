package com.meli.core.network.data.datasource

import kotlinx.coroutines.flow.Flow
import com.meli.core.network.data.model.CategoriesResponse

class MarketingPlaceDataSource(
    private val marketingPlaceService: MarketingPlaceService,
) {
    suspend fun getCategories(): List<CategoriesResponse> {
        return marketingPlaceService.getCategories()
    }
}