package com.meli.feature.productdetail.domain.provider

import com.meli.feature.productdetail.domain.model.ProductDetailModel
import com.meli.feature.productdetail.domain.usecase.ProductInfoUseCase
import com.meli.feature.productdetail.domain.usecase.ProductDescriptionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ProductDetailProvider(
    private val productInfoUseCase: ProductInfoUseCase,
    private val productDescriptionUseCase: ProductDescriptionUseCase,
) {
    suspend operator fun invoke(id: String): Flow<ProductDetailModel> {
        return combine(productInfoUseCase(id),productDescriptionUseCase(id)) { info, detail ->
            ProductDetailModel(
                title = info.title,
                description = detail.description,
                thumbnail = info.thumbnail,
                price = info.price,
                warranty = info.warranty,
            )
        }
    }
}