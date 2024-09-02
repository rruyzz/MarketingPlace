package com.meli.feature.search.domain.usecase

import com.meli.feature.search.domain.model.CategoriesModel
import com.meli.feature.search.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoriesUseCase(
    private val repository: CategoriesRepository
) {

    operator fun invoke(): Flow<List<CategoriesModel>> = flow {
        emit(repository.getCategories())
    }
}