package com.meli.feature.productlist.domain.di

import com.meli.feature.productlist.domain.provider.ProductListProvider
import com.meli.feature.productlist.domain.usecase.CategoryProductsUseCase
import com.meli.feature.productlist.domain.usecase.ProductListUseCase
import org.koin.dsl.module

object ProductListDomainModule {
    val productDomainModule = module {
        single { ProductListUseCase(repository = get()) }
        single { CategoryProductsUseCase(repository = get()) }
        single { ProductListProvider(productListUseCase = get(), categoryUseCase = get()) }
    }
}