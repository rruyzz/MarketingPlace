package com.meli.core.network.data.datasource

import retrofit2.http.GET
import com.meli.core.network.data.model.CategoriesResponse
import com.meli.core.network.data.model.ProductSearchResponse
import retrofit2.http.Query

interface MarketingPlaceService {

    @GET("categories")
    suspend fun getCategories(): List<CategoriesResponse>

    @GET("search")
    suspend fun getProduct(
        @Query("q") product: String? = null,
    ): ProductSearchResponse

    @GET("search")
    suspend fun getCategoryProduct(
        @Query("category") category: String? = null,
    ): ProductSearchResponse


}