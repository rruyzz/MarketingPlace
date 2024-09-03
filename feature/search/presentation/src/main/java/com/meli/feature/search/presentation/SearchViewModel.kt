package com.meli.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel(): ViewModel() {

    private val _searchAction = MutableSharedFlow<SearchAction>(0)
    val searchAction = _searchAction.asSharedFlow()

    fun onSearchClick(searchQuery: String) = viewModelScope.launch(Dispatchers.IO){
        _searchAction.emit(SearchAction.NavigateToProductList(searchQuery))
    }
}