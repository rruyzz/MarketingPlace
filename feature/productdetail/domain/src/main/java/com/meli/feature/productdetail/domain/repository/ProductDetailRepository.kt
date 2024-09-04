package com.meli.feature.productdetail.domain.repository

import com.meli.feature.productdetail.domain.model.ProductDescriptionModel
import com.meli.feature.productdetail.domain.model.ProductInfoModel

interface ProductDetailRepository {
    suspend fun getProductDetail(id: String) : ProductInfoModel
    suspend fun getProductDescription(id: String) : ProductDescriptionModel
}