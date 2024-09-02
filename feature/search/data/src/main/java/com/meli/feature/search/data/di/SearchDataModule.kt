package com.meli.feature.search.data.di
import com.meli.feature.search.data.repository.CategoriesRepositoryImpl
import com.meli.feature.search.domain.repository.CategoriesRepository
import org.koin.dsl.module

object SearchDataModule {
    val searchDataModule = module {
        single<CategoriesRepository> { CategoriesRepositoryImpl(getCategoriesUseCase = get()) }
    }
}