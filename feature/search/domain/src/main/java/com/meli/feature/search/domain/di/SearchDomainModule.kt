package com.meli.feature.search.domain.di

import com.meli.feature.search.domain.usecase.CategoriesUseCase
import org.koin.dsl.module

object SearchDomainModule {
    val searchDomainModule = module {
        single { CategoriesUseCase(repository = get()) }
    }
}