package com.meli.feature.productdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.feature.productdetail.domain.model.ProductDetailModel
import com.meli.feature.productdetail.domain.provider.ProductDetailProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val query: String,
    private val providerDetail: ProductDetailProvider,
) : ViewModel() {
    private val _categoriesDetailState = MutableSharedFlow<ProductDetailState>(0)
    val categoriesDetailState = _categoriesDetailState.asSharedFlow()

    init {
        getProductDetail()
    }

    private val data = ProductDetailModel(
        title = "PlayStation 5 - Edtion Fifa 25 Dois Controles",
        description = "Esse aqui eh o ps5, um video game muito bom com uma descricao muito detalhada para teste de view. essa tela aqui eh um saco de ser feita mas se deus quiser em breve estou acabando e faltara somente os testes unitarios talvez tambem oss testes de integracao vamo ve se vou ter tempo mas a realidade ehe que queria entregar do jeito que ta mesmo ja da pra ver o quao bem eu sei programar ja era deu" + "Esse aqui eh o ps5, um video game muito bom com uma descricao muito detalhada para teste de view. essa tela aqui eh um saco de ser feita mas se deus quiser em breve estou acabando e faltara somente os testes unitarios talvez tambem oss testes de integracao vamo ve se vou ter tempo mas a realidade ehe que queria entregar do jeito que ta mesmo ja da pra ver o quao bem eu sei programar ja era deu" + "Esse aqui eh o ps5, um video game muito bom com uma descricao muito detalhada para teste de view. essa tela aqui eh um saco de ser feita mas se deus quiser em breve estou acabando e faltara somente os testes unitarios talvez tambem oss testes de integracao vamo ve se vou ter tempo mas a realidade ehe que queria entregar do jeito que ta mesmo ja da pra ver o quao bem eu sei programar ja era deu",
        thumbnail = "http://http2.mlstatic.com/D_673378-MLU74442016885_022024-I.jpg",
        price = "R$ 5000,00 ",
        warranty = "Garantia de 6 meses"
    )

    fun getProductDetail() = viewModelScope.launch(Dispatchers.IO) {
        providerDetail(query)
            .flowOn(Dispatchers.IO)
            .onStart {
                _categoriesDetailState.emit(ProductDetailState(isLoading = true))
            }
            .onCompletion {
                _categoriesDetailState.emit(ProductDetailState(isLoading = false))
            }
            .catch {
                _categoriesDetailState.emit(ProductDetailState(error = it.message.orEmpty()))
            }
            .collect {
                _categoriesDetailState.emit(ProductDetailState(data = it))
            }
    }
}