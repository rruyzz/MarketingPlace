package com.meli.feature.productdetail.data.repository

import com.meli.core.network.domain.products.usecase.GetProductInfoUseCase
import com.meli.core.network.domain.products.usecase.GetProductDescriptionUseCase
import com.meli.feature.productdetail.data.mapper.toInfoDomain
import com.meli.feature.productdetail.data.mapper.toDescriptionDomain
import com.meli.feature.productdetail.domain.model.ProductDescriptionModel
import com.meli.feature.productdetail.domain.model.ProductInfoModel
import com.meli.feature.productdetail.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow

class ProductDetailRepositoryImpl(
    private val getProductInfoUseCase: GetProductInfoUseCase,
    private val getProductDescriptionUseCase: GetProductDescriptionUseCase,
) : ProductDetailRepository {
    override suspend fun getProductDetail(id: String): ProductInfoModel {
        return getProductInfoUseCase(id).toInfoDomain()
    }

    override suspend fun getProductDescription(id: String): ProductDescriptionModel {
        return getProductDescriptionUseCase(id).toDescriptionDomain()
    }
}