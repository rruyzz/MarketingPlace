package com.meli.core.network.domain.category.mapper

import com.meli.core.network.data.model.ProductDescriptionResponse
import com.meli.core.network.domain.products.model.ProductDescriptionDto

fun ProductDescriptionResponse.toDomain() = ProductDescriptionDto(
    description = this.description.orEmpty()
)