package com.meli.feature.productlist.domain.di

import com.meli.feature.productlist.domain.usecase.ProductListUseCase
import org.koin.dsl.module

object ProductListDomainModule {
    val productDomainModule = module {
        single { ProductListUseCase(repository = get()) }
    }
}