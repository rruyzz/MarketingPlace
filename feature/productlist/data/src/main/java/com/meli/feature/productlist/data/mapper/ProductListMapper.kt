package com.meli.feature.productlist.data.mapper

import com.meli.core.network.domain.products.model.ProductDto
import com.meli.feature.productlist.domain.model.ProductModel

fun List<ProductDto>.toDomain() = this.map { product ->
    ProductModel(
        id = product.id,
        title = product.title,
        price = product.price,
        thumbnail = product.thumbnail,
    )
}