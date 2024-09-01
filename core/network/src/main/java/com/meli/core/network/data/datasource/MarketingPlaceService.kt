package com.meli.core.network.data.datasource

import retrofit2.http.GET
import com.meli.core.network.data.model.CategoriesResponse

interface MarketingPlaceService {

    @GET("categories")
    suspend fun getCategories() : List<CategoriesResponse>

}