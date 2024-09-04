package com.meli.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ProductSearchResponse(
    @SerializedName("query") val query: String? = null,
    @SerializedName("site_id") val siteId: String? = null,
    @SerializedName("paging") val paging: PagingResponse? = null,
    @SerializedName("results") val results: List<ResultsItemResponse?>? = null
)

data class AttributesItemResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("value_name") val valueName: String? = null
)

data class PagingResponse(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("offset") val offset: Int? = null,
    @SerializedName("limit") val limit: Int? = null,
    @SerializedName("primary_results") val primaryResults: Int? = null
)

data class ResultsItemResponse(
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("available_quantity") val availableQuantity: Int? = null,
    @SerializedName("price") val price: Double? = null,
    @SerializedName("attributes") val attributes: List<AttributesItemResponse?>? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("currency_id") val currencyId: String? = null,
    @SerializedName("seller") val seller: Seller? = null
)

data class Seller(
    @SerializedName("nickname") val nickname: String? = null,
    @SerializedName("id") val id: Int? = null
)
