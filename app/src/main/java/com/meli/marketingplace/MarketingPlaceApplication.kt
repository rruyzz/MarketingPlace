package com.meli.marketingplace

import android.app.Application
import com.meli.core.network.di.NetworkModule.networkModule
import com.meli.feature.search.data.di.SearchDataModule.searchDataModule
import com.meli.feature.search.domain.di.SearchDomainModule.searchDomainModule
import com.meli.feature.search.presentation.di.SearchPresentationModule.searchPresentationModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


internal class MarketingPlaceApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MarketingPlaceApplication)
            modules(listOf(networkModule, appModule, searchDataModule, searchDomainModule, searchPresentationModule))
        }
    }

    private val appModule = module {
        viewModel {
            MainViewModel(get())
        }
    }
}