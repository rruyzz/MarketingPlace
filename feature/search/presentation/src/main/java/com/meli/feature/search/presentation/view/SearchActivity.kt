package com.meli.feature.search.presentation.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meli.feature.search.presentation.R
import com.meli.feature.search.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        actionObserver()
    }

    private fun actionObserver() = lifecycleScope.launch {
        val viewText = findViewById<TextView>(R.id.textIDearchd)
        viewModel.castMoviesState.collect { state ->
            viewText.text = state
        }
    }
}