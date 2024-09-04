package com.meli.core.navigation

import android.content.Context

interface ProductDetailNavigator {
    fun navigate(context: Context, query: String)
}