package com.meli.feature.productdetail.data.di

import com.meli.feature.productdetail.data.repository.ProductDetailRepositoryImpl
import com.meli.feature.productdetail.domain.repository.ProductDetailRepository
import org.koin.dsl.module

object ProductDetailDataModule {
    val productDetailDataModule = module {
        single<ProductDetailRepository> {
            ProductDetailRepositoryImpl(
                getProductInfoUseCase = get(),
                getProductDescriptionUseCase = get()
            )
        }
    }
}