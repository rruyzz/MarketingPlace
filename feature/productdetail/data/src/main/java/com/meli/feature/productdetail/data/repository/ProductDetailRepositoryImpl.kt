package com.meli.feature.productdetail.data.repository

import com.meli.feature.productdetail.domain.model.ProductDescriptionModel
import com.meli.feature.productdetail.domain.model.ProductInfoModel
import com.meli.feature.productdetail.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow

class ProductDetailRepositoryImpl(

) : ProductDetailRepository {
    override suspend fun getProductDetail(id: String): Flow<ProductInfoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDescription(id: String): Flow<ProductDescriptionModel> {
        TODO("Not yet implemented")
    }
}