package com.meli.marketingplace

import android.app.Application
import com.meli.core.network.di.NetworkModule.networkModule
import com.meli.feature.productdetail.data.di.ProductDetailDataModule.productDetailDataModule
import com.meli.feature.productdetail.domain.di.ProductDetailDomainData.productDetailDomainModule
import com.meli.feature.productdetail.presentation.di.ProductDetailPresentationModule.productDetailPresentationModule
import com.meli.feature.search.data.di.SearchDataModule.searchDataModule
import com.meli.feature.search.domain.di.SearchDomainModule.searchDomainModule
import com.meli.feature.search.presentation.di.SearchPresentationModule.searchPresentationModule
import com.meli.feature.productlist.data.di.ProductListDataModule.productListDataModule
import com.meli.feature.productlist.domain.di.ProductListDomainModule.productListDomainModule
import com.meli.feature.productlist.presentation.di.ProductListPresentationModule.productListPresentationModule
import com.meli.marketingplace.di.NavigatorModule.navigatorModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module


internal class MarketingPlaceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MarketingPlaceApplication)
            modules(
                listOf(
                    networkModule,
                    searchDataModule,
                    searchDomainModule,
                    searchPresentationModule,
                    productListDataModule,
                    productListDomainModule,
                    productListPresentationModule,
                    productDetailPresentationModule,
                    productDetailDomainModule,
                    productDetailDataModule,
                    navigatorModule,
                )
            )
        }
    }
}