package com.meli.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ProductSearchResponse(
    @SerializedName("query") val query: String? = null,
    @SerializedName("site_id") val siteId: String? = null,
    @SerializedName("paging") val paging: PagingResponse? = null,
    @SerializedName("results") val results: List<ResultsItemResponse?>? = null
)

data class AttributesItemResponse(
    @SerializedName("attribute_group_id") val attributeGroupId: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("attribute_group_name") val attributeGroupName: String? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("value_id") val valueId: String? = null,
//    @SerializedName("value_struct") val valueStruct: String? = null,
    @SerializedName("value_name") val valueName: String? = null
)

data class PagingResponse(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("offset") val offset: Int? = null,
    @SerializedName("limit") val limit: Int? = null,
    @SerializedName("primary_results") val primaryResults: Int? = null
)

data class ShippingResponse(
    @SerializedName("mode") val mode: String? = null,
    @SerializedName("free_shipping") val freeShipping: Boolean? = null,
    @SerializedName("tags") val tags: List<String?>? = null,
    @SerializedName("logistic_type") val logisticType: String? = null,
    @SerializedName("store_pick_up") val storePickUp: Boolean? = null
)

data class InstallmentsResponse(
    @SerializedName("amount") val amount: Double? = null,
    @SerializedName("quantity") val quantity: Int? = null,
    @SerializedName("rate") val rate: Double? = null,
    @SerializedName("currency_id") val currencyId: String? = null
)

data class ResultsItemResponse(
//    @SerializedName("original_price") val originalPrice: Double? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
//    @SerializedName("stop_time") val stopTime: String? = null,
//    @SerializedName("catalog_listing") val catalogListing: Boolean? = null,
//    @SerializedName("buying_mode") val buyingMode: String? = null,
//    @SerializedName("catalog_product_id") val catalogProductId: String? = null,
    @SerializedName("title") val title: String? = null,
//    @SerializedName("available_quantity") val availableQuantity: Int? = null,
//    @SerializedName("condition") val condition: String? = null,
//    @SerializedName("shipping") val shipping: ShippingResponse? = null,
//    @SerializedName("category_id") val categoryId: String? = null,
//    @SerializedName("installments") val installments: InstallmentsResponse? = null,
    @SerializedName("price") val price: Double? = null,
//    @SerializedName("site_id") val siteId: String? = null,
//    @SerializedName("official_store_id") val officialStoreId: Int? = null,
//    @SerializedName("attributes") val attributes: List<AttributesItemResponse?>? = null,
    @SerializedName("id") val id: String? = null,
//    @SerializedName("listing_type_id") val listingTypeId: String? = null,
//    @SerializedName("permalink") val permalink: String? = null,
//    @SerializedName("currency_id") val currencyId: String? = null,
//    @SerializedName("accepts_mercadopago") val acceptsMercadopago: Boolean? = null
)
//java.lang.NumberFormatException: Expected an int but was 6402814585011704 at line 1 column 3033 path $.results[0].attributes[3].source