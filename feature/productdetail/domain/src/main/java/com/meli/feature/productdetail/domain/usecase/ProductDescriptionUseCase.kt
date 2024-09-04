package com.meli.feature.productdetail.domain.usecase

import com.meli.feature.productdetail.domain.model.ProductDescriptionModel
import com.meli.feature.productdetail.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow

class ProductDescriptionUseCase(
    private val repository: ProductDetailRepository
) {

    suspend operator fun invoke(id: String) : Flow<ProductDescriptionModel> {
        return repository.getProductDescription(id)
    }
}