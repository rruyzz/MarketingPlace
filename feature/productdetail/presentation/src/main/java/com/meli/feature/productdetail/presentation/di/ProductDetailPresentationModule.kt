package com.meli.feature.productdetail.presentation.di

import com.meli.feature.productdetail.presentation.ProductDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ProductDetailPresentationModule {
    val productDetailPresentationModule = module {
        viewModel { parameters ->
            ProductDetailViewModel(parameters.get())
        }
    }
}