package com.meli.feature.productdetail.domain.repository

import com.meli.feature.productdetail.domain.model.ProductDescriptionModel
import com.meli.feature.productdetail.domain.model.ProductInfoModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    suspend fun getProductDetail(id: String) : Flow<ProductInfoModel>
    suspend fun getProductDescription(id: String) : Flow<ProductDescriptionModel>
}