package com.meli.core.network.domain.productDetail.usecase

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.domain.productDetail.mapper.toDomain
import com.meli.core.network.domain.productDetail.model.ProductDescriptionDto

class GetProductDescriptionUseCase(
    private val dataSource: MarketingPlaceDataSource
)  {

    suspend operator fun invoke(
        product: String
    ) : ProductDescriptionDto {
        return dataSource.getProductDescription(product).toDomain()
    }
}