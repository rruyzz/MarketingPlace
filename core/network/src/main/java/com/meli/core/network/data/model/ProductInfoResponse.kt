package com.meli.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ProductInfoResponse(
    @SerializedName("title") val title: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("price") val price: String? = null,
    @SerializedName("warranty") val warranty: String? = null,
)