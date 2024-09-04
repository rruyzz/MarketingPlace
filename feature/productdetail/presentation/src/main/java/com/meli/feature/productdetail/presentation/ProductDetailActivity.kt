package com.meli.feature.productdetail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.meli.feature.productdetail.presentation.databinding.ActivityProductDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailActivity : AppCompatActivity() {

    private val binding: ActivityProductDetailBinding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<ProductDetailViewModel> {
        parametersOf(intent.extras?.getString("id"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        stateObserver()
        viewModel.getProductDetail()
    }

    private fun stateObserver() = lifecycleScope.launch {
        viewModel.categoriesDetailState.collect { state ->
            binding.titleProduct.text = state.data?.title
            Glide.with(this@ProductDetailActivity).load(state.data?.thumbnail).into(binding.productImage)
            binding.description.text = state.data?.description
            binding.textWarrenty.text = state.data?.warranty
            binding.textValue.text = state.data?.price
            binding.progress.isVisible = state.isLoading
        }
    }

    companion object {
        fun getIntent(context: Context, id: String): Intent {
            return Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("id", id)
            }
        }
    }
}