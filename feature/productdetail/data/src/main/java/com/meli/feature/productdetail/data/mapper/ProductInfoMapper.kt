package com.meli.feature.productdetail.data.mapper

import com.meli.feature.productdetail.domain.model.ProductInfoModel
import com.meli.core.network.domain.products.model.ProductInfoDto

fun ProductInfoDto.toInfoDomain() = ProductInfoModel(
    title = this.title,
    thumbnail = this.thumbnail,
    price = this.price,
    warranty = this.warranty,
)