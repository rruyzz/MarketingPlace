package com.meli.feature.search.presentation.di

import com.meli.feature.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object SearchPresentationModule {
    val searchPresentationModule = module {
        viewModel { SearchViewModel(getCategoriesUseCase = get()) }
    }
}