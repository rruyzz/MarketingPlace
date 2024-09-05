package com.meli.feature.productlist.presentation

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.domain.provider.ProductListProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ProductListViewModelTest {

    private val productListProvider: ProductListProvider = mock<ProductListProvider>()
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var testRule = CoroutineTestRule()


    @Test
    fun `onTryAgainDialogClick Should emit Success State`() = runTest(testDispatcher) {
        val viewModel = ProductListViewModel("query", false, productListProvider)
        whenever(productListProvider("query", false)) doReturn flowOf(listOf(ProductModel("id", "title", "price", "thumbnail")))

        viewModel.onTryAgainDialogClick()

        viewModel.productListState.test {
            assertEquals(
                ProductListState(productList = listOf(ProductModel("id", "title", "price", "thumbnail"))),
                awaitItem()
            )
        }
    }


    @Test
    fun `onProductClick Should emit NavigateToProductDetail`() = runTest {
        turbineScope {

            // Given
            val viewModel = ProductListViewModel("query", false, productListProvider)
            val productListAction = viewModel.productListAction.testIn(backgroundScope)

            // When
            viewModel.onProductClick("id")


            // Then
            assertEquals(ProductListAction.NavigateToProductDetail("id"), productListAction.awaitItem())
        }
    }

    @Test
    fun `onToolbarClick Should emit OnBackPressed`() = runTest {
        turbineScope {

            // Given
            val viewModel = ProductListViewModel("query", false, productListProvider)
            val productListAction = viewModel.productListAction.testIn(backgroundScope)

            // When
            viewModel.onToolbarClick()


            // Then
            assertEquals(ProductListAction.OnBackPressed, productListAction.awaitItem())
        }
    }

    @Test
    fun `onCancelDialogClick Should emit OnBackPressed`() = runTest {
        turbineScope {

            // Given
            val viewModel = ProductListViewModel("query", false, productListProvider)
            val productListAction = viewModel.productListAction.testIn(backgroundScope)

            // When
            viewModel.onCancelDialogClick()


            // Then
            assertEquals(ProductListAction.OnBackPressed, productListAction.awaitItem())
        }
    }

}