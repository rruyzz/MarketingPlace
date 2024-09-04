package com.meli.core.network.domain.productDetail.usecase

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.domain.productDetail.mapper.toDomain
import com.meli.core.network.domain.productDetail.model.ProductInfoDto

class GetProductInfoUseCase(
    private val dataSource: MarketingPlaceDataSource
)  {
    suspend operator fun invoke(
        product: String
    ) : ProductInfoDto {
        return dataSource.getProductInfo(product).toDomain()
    }
}