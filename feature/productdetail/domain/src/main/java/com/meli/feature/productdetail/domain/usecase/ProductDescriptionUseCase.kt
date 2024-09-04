package com.meli.feature.productdetail.domain.usecase

import com.meli.feature.productdetail.domain.model.ProductDescriptionModel
import com.meli.feature.productdetail.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductDescriptionUseCase(
    private val repository: ProductDetailRepository
) {

    suspend operator fun invoke(id: String) : Flow<ProductDescriptionModel> = flow {
        emit(repository.getProductDescription(id))
    }
}