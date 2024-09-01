package com.meli.core.network.domain

import com.meli.core.network.data.datasource.MarketingPlaceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoriesUseCase(
    private val dataSource: MarketingPlaceDataSource
) {

    operator fun invoke() : Flow<String> {
        return flow {
            emit(dataSource.getCategories().first().id)
        }
    }
}