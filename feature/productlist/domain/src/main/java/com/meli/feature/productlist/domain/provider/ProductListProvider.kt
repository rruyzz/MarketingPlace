package com.meli.feature.productlist.domain.provider

import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.usecase.CategoryProductsUseCase
import com.meli.feature.productlist.domain.usecase.ProductListUseCase
import kotlinx.coroutines.flow.Flow

class ProductListProvider(
    private val productListUseCase: ProductListUseCase,
    private val categoryUseCase: CategoryProductsUseCase,
) {

    operator fun invoke(query: String, isCategory: Boolean): Flow<List<ProductModel>> {
        return if (isCategory) categoryUseCase(query)
        else productListUseCase(query)
    }
}