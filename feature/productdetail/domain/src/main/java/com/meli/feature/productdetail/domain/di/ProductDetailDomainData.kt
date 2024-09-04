package com.meli.feature.productdetail.domain.di

import org.koin.dsl.module
import com.meli.feature.productdetail.domain.provider.ProductDetailProvider
import com.meli.feature.productdetail.domain.usecase.ProductDescriptionUseCase
import com.meli.feature.productdetail.domain.usecase.ProductInfoUseCase

object ProductDetailDomainData {

    val productDetailDomainModule = module {
        single { ProductDescriptionUseCase(repository = get()) }
        single { ProductInfoUseCase(repository = get()) }
        single {
            ProductDetailProvider(
                productInfoUseCase = get(),
                productDescriptionUseCase = get()
            )
        }
    }
}