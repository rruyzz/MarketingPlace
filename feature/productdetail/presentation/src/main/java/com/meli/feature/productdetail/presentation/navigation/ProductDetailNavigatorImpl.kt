package com.meli.feature.productdetail.presentation.navigation

import android.content.Context
import com.meli.core.navigation.ProductDetailNavigator
import com.meli.feature.productdetail.presentation.ProductDetailActivity

class ProductDetailNavigatorImpl : ProductDetailNavigator {
    override fun navigate(context: Context, query: String) {
        context.startActivity(ProductDetailActivity.getIntent(context, query))
    }
}