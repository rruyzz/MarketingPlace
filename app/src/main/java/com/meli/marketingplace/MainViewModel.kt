package com.meli.marketingplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.core.network.domain.GetCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private val _castMoviesState = MutableSharedFlow<String>(0)
    val castMoviesState = _castMoviesState.asSharedFlow()

    init {
        teste()
    }

    fun teste() = viewModelScope.launch(Dispatchers.IO) {
        getCategoriesUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _castMoviesState.emit("CastState.Loading(true)") }
            .onCompletion {
            }
            .catch {
                _castMoviesState.emit("CastState.Error(it.message.orEmpty())")
            }
            .collect {
                _castMoviesState.emit(it.first().toString())
            }
    }
}