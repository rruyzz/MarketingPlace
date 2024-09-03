package com.meli.core.network.domain.products.usecase

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.domain.products.mapper.toDomain
import com.meli.core.network.domain.products.model.ProductDto

class GetProductsUseCase(
    private val dataSource: MarketingPlaceDataSource
) {

    suspend operator fun invoke(
        product: String
    ) : List<ProductDto> {
        return dataSource.getProduct(product).toDomain()
    }
}