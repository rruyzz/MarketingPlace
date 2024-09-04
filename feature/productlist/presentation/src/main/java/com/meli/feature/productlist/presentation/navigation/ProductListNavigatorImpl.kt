package com.meli.feature.productlist.presentation.navigation

import android.content.Context
import com.meli.core.navigation.ProductListNavigator
import com.meli.feature.productlist.presentation.ProductListActivity

class ProductListNavigatorImpl : ProductListNavigator {
    override fun navigate(context: Context, query: String, isCategory: Boolean) {
        context.startActivity(ProductListActivity.getIntent(context, query, isCategory))
    }
}