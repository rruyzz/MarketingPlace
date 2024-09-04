package com.meli.feature.productdetail.data.mapper

import com.meli.core.network.domain.products.model.ProductDescriptionDto
import com.meli.feature.productdetail.domain.model.ProductDescriptionModel

fun ProductDescriptionDto.toDescriptionDomain() = ProductDescriptionModel(
    description = this.description
)