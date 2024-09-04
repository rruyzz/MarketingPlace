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
import com.meli.feature.productlist.domain.model.ProductModel
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
        setToolbar()
    }

    private fun stateObserver() = lifecycleScope.launch {
        viewModel.productListState.collect { state ->
            setCategoriesList(state.productList.orEmpty())
            setLoading(state.isLoading)
        }
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.productListAction.collect { action ->
            when (action) {
                is ProductListAction.NavigateToProductDetail -> navigator(action.id)
                is ProductListAction.OnBackPressed -> onBackPressedDispatcher.onBackPressed()

            }
        }
    }

    private fun setToolbar() = with(binding) {
        toolbar.setNavigationOnClickListener {
            viewModel.onToolbarClick()
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setCategoriesList(categoriesList: List<ProductModel>) = with(binding) {
        productRecycleView.adapter =
            ProductAdapter(categoriesList.orEmpty(), ::onClick, this@ProductListActivity)
        productRecycleView.layoutManager = GridLayoutManager(this@ProductListActivity, 2)
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progress.isVisible = isLoading
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