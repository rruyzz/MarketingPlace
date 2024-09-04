package com.meli.core.network.domain.category.mapper

import com.meli.core.network.data.model.ProductInfoResponse
import com.meli.core.network.domain.products.model.ProductInfoDto

fun ProductInfoResponse.toDomain() = ProductInfoDto(
    title = this.title.orEmpty(),
    thumbnail = this.thumbnail.orEmpty(),
    price = this.price.orEmpty(),
    warranty = this.warranty.orEmpty()
)