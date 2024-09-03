package com.meli.feature.productlist.domain.usecase

import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListUseCase(
    private val repository: ProductListRepository
) {

    operator fun invoke(product: String): Flow<List<ProductModel>> = flow {
        emit(repository.getProductList(product))
    }
}