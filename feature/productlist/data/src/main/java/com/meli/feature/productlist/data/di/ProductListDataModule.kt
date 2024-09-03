package com.meli.feature.productlist.data.di

import com.meli.feature.productlist.data.repository.ProductListRepositoryImpl
import com.meli.feature.productlist.domain.repository.ProductListRepository
import org.koin.dsl.module

object ProductListDataModule {
    val productListDataModule = module {
        single<ProductListRepository> { ProductListRepositoryImpl(getProductUseCase = get()) }
    }
}