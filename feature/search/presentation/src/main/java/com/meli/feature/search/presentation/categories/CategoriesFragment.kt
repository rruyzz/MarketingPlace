package com.meli.feature.search.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.meli.core.navigation.ProductListNavigator
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.meli.feature.search.presentation.databinding.FragmentCategoriesBinding
import com.meli.feature.search.presentation.categories.adapter.CategoriesAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val productNavigation by inject<ProductListNavigator>()
    private val viewModel: CategoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionObserver()
        stateObserver()
    }

    private fun stateObserver() = lifecycleScope.launch {
        viewModel.categoriesState.collect { state ->
            binding.categoriesRecycleView.adapter =
                CategoriesAdapter(state.categoriesList.orEmpty(), ::onClick)
            binding.categoriesRecycleView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.progress.isVisible = state.isLoading
        }
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.categoryAction.collect { action ->
            when (action) {
                is CategoriesAction.NavigateToProductList -> navigateToProductList(action.categorieId)
            }
        }
    }

    private fun onClick(id: String) {
        viewModel.onCategoryClick(id)
    }

    private fun navigateToProductList(categorieId: String) {
        productNavigation.navigate(requireContext(), categorieId, true)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}