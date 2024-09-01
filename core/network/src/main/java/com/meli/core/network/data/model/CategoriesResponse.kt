package com.meli.core.network.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,

)