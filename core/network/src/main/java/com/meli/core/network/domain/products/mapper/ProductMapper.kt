package com.meli.core.network.domain.products.mapper

import com.meli.core.network.data.model.ProductSearchResponse
import com.meli.core.network.domain.products.model.ProductDto
import java.text.NumberFormat
import java.util.Currency

fun ProductSearchResponse.toDomain() = this.results?.map { result ->
    ProductDto(
        id = result?.id.orEmpty(),
        title = result?.title.orEmpty(),
        price = result?.price.toMoneyFormat(),
        thumbnail = result?.thumbnail.orEmpty()
    )
} ?: listOf()

fun Double?.toMoneyFormat(): String {
    val format = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(0);
    format.setCurrency(Currency.getInstance("BRL"));
    return format.format(this)
}