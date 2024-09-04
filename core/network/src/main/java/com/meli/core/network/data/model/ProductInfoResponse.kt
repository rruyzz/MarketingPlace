package com.meli.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ProductInfoResponse(
    @SerializedName("title") val title: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("price") val price: Double? = null,
    @SerializedName("warranty") val warranty: String? = null,
    @SerializedName("currency_id") val currencyId: String? = null,
)