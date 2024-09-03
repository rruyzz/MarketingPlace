package com.meli.core.network.di

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import com.meli.core.network.data.datasource.MarketingPlaceService
import com.meli.core.network.domain.category.usecase.GetCategoriesUseCase
import com.meli.core.network.domain.products.usecase.GetCategoryProductsUseCase
import com.meli.core.network.domain.products.usecase.GetProductsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

const val CONNECT_TIMEOUT = 15L
const val WRITE_TIMEOUT = 15L
const val READ_TIMEOUT = 15L

object NetworkModule {

    val networkModule = module {
        single { createHttpClient() }
        single { retrofitClient(get()) }
        single { MarketingPlaceDataSource(get()) }
        single { GetCategoriesUseCase(dataSource = get()) }
        single { GetProductsUseCase(dataSource = get()) }
        single { GetCategoryProductsUseCase(dataSource = get()) }
        single(createdAtStart = false) { get<Retrofit>().create(MarketingPlaceService::class.java) }
    }

    private fun retrofitClient(
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/MLB/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private fun createHttpClient(): OkHttpClient {
        val loggin = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggin)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(5 * 60, TimeUnit.SECONDS)
        }
        return client.build()
    }
}