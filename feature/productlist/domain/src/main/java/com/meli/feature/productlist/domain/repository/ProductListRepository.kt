package com.meli.feature.productlist.domain.repository

import com.meli.feature.productlist.domain.model.ProductModel

interface ProductListRepository {
    suspend fun getProductList(product: String): List<ProductModel>
    suspend fun getCategoryProductList(category: String): List<ProductModel>
}