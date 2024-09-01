package com.meli.marketingplace

import android.app.Application
import com.meli.core.network.NetworkModule.retrofitModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

internal class MarketingPlaceApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarketingPlaceApplication)
            modules(listOf(retrofitModule))
        }
    }
}