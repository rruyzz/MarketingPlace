package com.meli.marketingplace.di

import com.meli.core.navigation.ProductDetailNavigator
import com.meli.core.navigation.ProductListNavigator
import com.meli.feature.productdetail.presentation.navigation.ProductDetailNavigatorImpl
import com.meli.feature.productlist.presentation.navigation.ProductListNavigatorImpl
import org.koin.dsl.module

object NavigatorModule {
    val navigatorModule = module {
        single<ProductListNavigator> { ProductListNavigatorImpl() }
        single<ProductDetailNavigator> { ProductDetailNavigatorImpl() }
    }
}