package com.meli.core.network.data.datasource

import retrofit2.http.GET
import com.meli.core.network.data.model.CategoriesResponse
import com.meli.core.network.data.model.ProductDescriptionResponse
import com.meli.core.network.data.model.ProductInfoResponse
import com.meli.core.network.data.model.ProductSearchResponse
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketingPlaceService {

    @GET("MLB/categories")
    suspend fun getCategories(): List<CategoriesResponse>

    @GET("MLB/search")
    suspend fun getProduct(
        @Query("q") product: String? = null,
    ): ProductSearchResponse

    @GET("MLB/search")
    suspend fun getCategoryProduct(
        @Query("category") category: String? = null,
    ): ProductSearchResponse

    @GET("items/{id}")
    suspend fun getProductInfo(
        @Path("id") id: String? = null,
    ): ProductInfoResponse

    @GET("items/{id}/description")
    suspend fun getProductDescription(
        @Path("id") category: String? = null,
    ): ProductDescriptionResponse
}