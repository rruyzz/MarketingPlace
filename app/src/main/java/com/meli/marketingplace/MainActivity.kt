package com.meli.marketingplace

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        actionObserver()
    }

    private fun actionObserver() = lifecycleScope.launch {
        val viewText = findViewById<TextView>(R.id.textId)
        viewModel.castMoviesState.collect { state ->
            viewText.text = state
            }
        }
}