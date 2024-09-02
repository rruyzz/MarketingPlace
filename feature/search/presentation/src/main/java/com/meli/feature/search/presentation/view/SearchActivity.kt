package com.meli.feature.search.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.search.SearchView
import com.meli.feature.search.presentation.R
import com.meli.feature.search.presentation.databinding.ActivitySearchBinding
import com.meli.feature.search.presentation.viewmodel.SearchViewModel
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
        viewModel.castMoviesState.collect { state ->
            binding.text1.text = state
        }
    }

    private fun searchBar() {
        binding.apply {
            searchView.setupWithSearchBar(searchBar)
        }
    }
    private fun setButtons() {
        binding.apply {
            searchView.editText.setOnEditorActionListener { textView, i, keyEvent ->
                val queryText = textView.text.toString()
                searchBar.setText(queryText)
                Toast.makeText(this@SearchActivity, queryText, Toast.LENGTH_SHORT).show()
                searchView.hide()
                return@setOnEditorActionListener false
            }
        }
    }
}