package com.meli.core.network.data.datasource

import kotlinx.coroutines.flow.Flow
import com.meli.core.network.data.model.CategoriesResponse
import com.meli.core.network.data.model.ProductSearchResponse

class MarketingPlaceDataSource(
    private val marketingPlaceService: MarketingPlaceService,
) {
    suspend fun getCategories(): List<CategoriesResponse> {
        return marketingPlaceService.getCategories()
    }

    suspend fun getProduct(product: String): ProductSearchResponse {
        return marketingPlaceService.getProduct(product)
    }

    suspend fun getCategoryProduct(category: String): ProductSearchResponse {
        return marketingPlaceService.getCategoryProduct(category)
    }
}