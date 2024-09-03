package com.meli.feature.search.presentation.di

import com.meli.feature.search.presentation.categories.CategoriesViewModel
import com.meli.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object SearchPresentationModule {
    val searchPresentationModule = module {
        viewModel { SearchViewModel() }
        viewModel { CategoriesViewModel(getCategoriesUseCase = get()) }
    }
}