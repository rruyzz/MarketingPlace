package com.meli.core.network.domain.productDetail.mapper

import com.meli.core.common.extensions.toMoneyFormat
import com.meli.core.network.data.model.ProductInfoResponse
import com.meli.core.network.domain.productDetail.model.ProductInfoDto

fun ProductInfoResponse.toDomain() = ProductInfoDto(
    title = this.title.orEmpty(),
    thumbnail = this.thumbnail.orEmpty(),
    price = this.price.toMoneyFormat(this.currencyId ?: "BRL"),
    warranty = this.warranty.orEmpty()
)