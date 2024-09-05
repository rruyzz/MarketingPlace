package com.meli.feature.search.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.meli.core.common.widgets.showDialogError
import com.meli.core.navigation.ProductListNavigator
import com.meli.feature.search.domain.model.CategoriesModel
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

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.categoryAction.collect { action ->
            when (action) {
                is CategoriesAction.NavigateToProductList -> navigateToProductList(action.categorieId)
                is CategoriesAction.OnBackAction -> requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun stateObserver() = lifecycleScope.launch {
        viewModel.categoriesState.collect { state ->
            setCategoriesList(state.categoriesList)
            setLoading(state.isLoading)
            state.throwable?.let { showDialogDefaultError() }
        }
    }

    private fun showDialogDefaultError() {
        requireActivity().showDialogError(
            onCancelClick = { viewModel.onCancelDialogClick() },
            onTryAgain = { viewModel.onTryAgainDialogClick() }
        )
    }

    private fun setCategoriesList(categoriesList: List<CategoriesModel>?) = with(binding) {
        categoriesRecycleView.adapter = CategoriesAdapter(categoriesList.orEmpty(), ::onClick)
        categoriesRecycleView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progress.isVisible = isLoading
    }

    private fun onClick(id: String) {
        viewModel.onCategoryClick(id)
    }

    private fun navigateToProductList(categorieId: String) {
        productNavigation.navigate(requireContext(), categorieId, true)
    }
}