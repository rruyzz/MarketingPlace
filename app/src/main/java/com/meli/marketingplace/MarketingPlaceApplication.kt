package com.meli.marketingplace

import android.app.Application
import com.meli.core.network.di.NetworkModule.networkModule
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
            modules(listOf(networkModule, appModule))
        }
    }

    private val appModule = module {
        viewModel {
            MainViewModel(get())
        }
    }
}