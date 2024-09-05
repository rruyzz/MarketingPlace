package com.meli.feature.productdetail.presentation

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.meli.feature.productdetail.domain.model.ProductDetailModel
import com.meli.feature.productdetail.domain.provider.ProductDetailProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ProductDetailViewModelTest {

    private val productListProvider: ProductDetailProvider = mock<ProductDetailProvider>()
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var testRule = CoroutineTestRule()


    @Test
    fun `onTryAgainDialogClick Should emit Success State`() = runTest(testDispatcher) {
        // Given
        val viewModel = ProductDetailViewModel("query", productListProvider)
        val expetedResult =
            ProductDetailModel("title", "description", "thumbnail", "price", "warranty")
        whenever(productListProvider("query")) doReturn flowOf(expetedResult)

        // When
        viewModel.onTryAgainDialogClick()

        // Then
        viewModel.productDetailState.test {
            assertEquals(
                ProductDetailState(productDetail = expetedResult),
                awaitItem()
            )
        }
    }

    @Test
    fun `onToolbarClick Should emit OnBackPressed`() = runTest {
        turbineScope {

            // Given
            val viewModel = ProductDetailViewModel("query", productListProvider)
            val productDetailAction = viewModel.productDetailAction.testIn(backgroundScope)

            // When
            viewModel.onToolbarClick()


            // Then
            assertEquals(ProductDetailAction.OnBackPressed, productDetailAction.awaitItem())
        }
    }

    @Test
    fun `onCancelDialogClick Should emit OnBackPressed`() = runTest {
        turbineScope {

            // Given
            val viewModel = ProductDetailViewModel("query", productListProvider)
            val productDetailAction = viewModel.productDetailAction.testIn(backgroundScope)

            // When
            viewModel.onCancelDialogClick()


            // Then
            assertEquals(ProductDetailAction.OnBackPressed, productDetailAction.awaitItem())
        }
    }
}