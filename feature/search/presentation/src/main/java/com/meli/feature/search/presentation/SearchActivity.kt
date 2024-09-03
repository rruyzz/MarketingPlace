package com.meli.feature.search.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meli.feature.search.presentation.databinding.ActivitySearchBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel by viewModel<SearchViewModel>()
    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setButtons()
        searchBar()
        actionObserver()
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.searchAction.collect { action ->
            when(action) {
                is SearchAction.NavigateToProductList -> navigateToProductList(action.product)
            }
        }
    }

    private fun navigateToProductList(product: String) {
        Toast.makeText(this, product, Toast.LENGTH_SHORT).show()
    }


    private fun searchBar() {
        binding.apply {
            searchView.setupWithSearchBar(searchBar)
        }
    }

    private fun setButtons() {
        binding.apply {
            searchView.editText.setOnEditorActionListener { textView, _, _ ->
                val queryText = textView.text.toString()
                searchBar.setText(queryText)
                searchView.hide()
                viewModel.onSearchClick(queryText)
                return@setOnEditorActionListener false
            }
        }
    }
}