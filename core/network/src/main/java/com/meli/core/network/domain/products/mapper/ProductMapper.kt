package com.meli.core.network.domain.products.mapper

import com.meli.core.common.extensions.toMoneyFormat
import com.meli.core.network.data.model.ProductSearchResponse
import com.meli.core.network.domain.products.model.ProductDto

fun ProductSearchResponse.toDomain() = this.results?.map { result ->
    ProductDto(
        id = result?.id.orEmpty(),
        title = result?.title.orEmpty(),
        price = result?.price.toMoneyFormat(result?.currencyId ?: "BRL"),
        thumbnail = result?.thumbnail.orEmpty()
    )
} ?: listOf()

