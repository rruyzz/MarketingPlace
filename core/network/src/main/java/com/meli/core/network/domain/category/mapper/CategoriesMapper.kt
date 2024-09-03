package com.meli.core.network.domain.category.mapper

import com.meli.core.network.data.model.CategoriesResponse
import com.meli.core.network.domain.category.model.CategoriesDto

fun List<CategoriesResponse>.toDomain() = this.map { categories ->
    CategoriesDto(
        id = categories.id,
        name = categories.name,
    )
}