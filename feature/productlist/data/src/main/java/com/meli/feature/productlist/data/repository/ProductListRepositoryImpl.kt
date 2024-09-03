package com.meli.feature.productlist.data.repository

import com.meli.core.network.domain.products.usecase.GetProductsUseCase
import com.meli.feature.productlist.data.mapper.toDomain
import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.repository.ProductListRepository

class ProductListRepositoryImpl(
    private val getProductUseCase: GetProductsUseCase
) : ProductListRepository {
    override suspend fun getProductList(product: String): List<ProductModel> {
        return getProductUseCase.invoke(product = product).toDomain()
    }
}