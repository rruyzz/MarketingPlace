package com.meli.feature.productlist.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.meli.feature.productlist.presentation.ProductListViewModel

object ProductListPresentationModule {
    val productListPresentationModule = module {
        viewModel { ProductListViewModel(get()) }
    }
}