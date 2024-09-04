package com.meli.feature.productlist.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meli.feature.productlist.presentation.databinding.ActivityProductListBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Context
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.meli.core.navigation.ProductDetailNavigator
import com.meli.feature.productlist.presentation.adapter.ProductAdapter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductListActivity : AppCompatActivity() {

    private val viewModel by viewModel<ProductListViewModel> {
        parametersOf(
            intent.extras?.getString("query"),
            intent.extras?.getBoolean("isCategory"),
        )
    }

    private val productNavigation by inject<ProductDetailNavigator>()

    private val binding: ActivityProductListBinding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        stateObserver()
        actionObserver()
    }

    private fun stateObserver() = lifecycleScope.launch {
        viewModel.categoriesState.collect { state ->
            binding.productRecycleView.adapter = ProductAdapter(state.productList, ::onClick, this@ProductListActivity)
            binding.productRecycleView.layoutManager = GridLayoutManager(this@ProductListActivity, 2)
            binding.progress.isVisible = state.isLoading
        }
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.categoriesAction.collect { action ->
            when(action) {
                is ProductListAction.NavigateToProductDetail -> navigator(action.id)
            }
        }
    }

    private fun navigator(id: String) {
        productNavigation.navigate(this, id)
    }

    private fun onClick(id: String) {
        viewModel.onProductClick(id)
    }

    companion object {
        fun getIntent(context: Context, query: String, isCategory: Boolean): Intent {
            return Intent(context, ProductListActivity::class.java).apply {
                putExtra("query", query)
                putExtra("isCategory", isCategory)
            }
        }
    }
}