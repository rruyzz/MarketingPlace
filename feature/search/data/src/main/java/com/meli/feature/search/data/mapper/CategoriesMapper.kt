package com.meli.feature.search.data.mapper

import com.meli.core.network.domain.category.model.CategoriesDto
import com.meli.feature.search.domain.model.CategoriesModel

internal fun List<CategoriesDto>.toDomain() = this.map { categoriesDto ->
    CategoriesModel(
        id = categoriesDto.id,
        name = categoriesDto.name
    )
}