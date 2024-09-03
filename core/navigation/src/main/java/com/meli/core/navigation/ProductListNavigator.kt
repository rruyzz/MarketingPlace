package com.meli.core.navigation

import android.content.Context

interface ProductListNavigator {
    fun navigate(context: Context, query: String, isCategory: Boolean)
}