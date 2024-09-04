package com.meli.core.network.domain.products.usecase

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.domain.category.mapper.toDomain
import com.meli.core.network.domain.products.mapper.toDomain
import com.meli.core.network.domain.products.model.ProductDto
import com.meli.core.network.domain.products.model.ProductInfoDto

class GetProductInfoUseCase(
    private val dataSource: MarketingPlaceDataSource
)  {
    suspend operator fun invoke(
        product: String
    ) : ProductInfoDto {
        return dataSource.getProductInfo(product).toDomain()
    }
}