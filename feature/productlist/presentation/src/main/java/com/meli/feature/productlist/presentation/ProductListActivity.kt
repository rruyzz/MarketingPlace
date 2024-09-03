package com.meli.feature.productlist.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meli.feature.productlist.presentation.databinding.ActivityProductListBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListActivity : AppCompatActivity() {

    private val viewModel by viewModel<ProductListViewModel>()
    private val binding: ActivityProductListBinding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        stateObserver()
    }

    private fun stateObserver() = lifecycleScope.launch {
        viewModel.categoriesState.collect { state ->
            binding.textRodolfo.text  = state.productList.toString()
        }
    }
}