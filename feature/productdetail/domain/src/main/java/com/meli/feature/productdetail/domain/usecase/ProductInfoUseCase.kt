package com.meli.feature.productdetail.domain.usecase

import com.meli.feature.productdetail.domain.model.ProductInfoModel
import com.meli.feature.productdetail.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductInfoUseCase(
    private val repository: ProductDetailRepository
) {

    suspend operator fun invoke(id: String) : Flow<ProductInfoModel> = flow {
        emit(repository.getProductDetail(id))
    }

}