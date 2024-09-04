package com.meli.core.network.domain.productDetail.mapper

import com.meli.core.network.data.model.ProductDescriptionResponse
import com.meli.core.network.domain.productDetail.model.ProductDescriptionDto

fun ProductDescriptionResponse.toDomain() = ProductDescriptionDto(
    description = this.description.orEmpty()
)