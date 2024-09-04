package com.meli.feature.productlist.domain.usecase

import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryProductsUseCase(
    private val repository: ProductListRepository
) {
    operator fun invoke(category: String): Flow<List<ProductModel>> = flow {
        emit(repository.getCategoryProductList(category))
    }
}