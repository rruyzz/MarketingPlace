package com.meli.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ProductDescriptionResponse(
    @SerializedName("plain_text") val description: String? = null
)