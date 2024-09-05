package com.meli.feature.search.presentation.categories

import app.cash.turbine.test
import org.junit.Test
import com.meli.feature.search.domain.model.CategoriesModel
import com.meli.feature.search.domain.usecase.CategoriesUseCase
import app.cash.turbine.turbineScope
import com.meli.feature.search.presentation.CoroutineTestRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class CategoriesViewModelTest {

    private val categorieUseCase: CategoriesUseCase = mock<CategoriesUseCase>()
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var testRule = CoroutineTestRule()

    @Test
    fun `onTryAgainDialogClick Should emit Success State`() = runTest(testDispatcher) {
        val viewModel = CategoriesViewModel(categorieUseCase)
        whenever(categorieUseCase()) doReturn flowOf(listOf(CategoriesModel("id", "rodolfo")))

        viewModel.onTryAgainDialogClick()

        viewModel.categoriesState.test {
            assertEquals(
                CategoriesState(categoriesList = listOf(CategoriesModel("id", "rodolfo"))),
                awaitItem()
            )
        }
    }

    @Test
    fun `onCancelDialogClick Should emit OnBackAction`() = runTest {
        turbineScope {

            // Given
            val viewModel = CategoriesViewModel(categorieUseCase)
            val categoriesAction = viewModel.categoryAction.testIn(backgroundScope)

            // When
            viewModel.onCancelDialogClick()


            // Then
            assertEquals(CategoriesAction.OnBackAction, categoriesAction.awaitItem())
        }
    }

    @Test
    fun `onCategoryClick Should emit OnBackAction`() = runTest {
        turbineScope {

            // Given
            val viewModel = CategoriesViewModel(categorieUseCase)
            val categoriesAction = viewModel.categoryAction.testIn(backgroundScope)

            // When
            viewModel.onCategoryClick("id")


            // Then
            assertEquals(CategoriesAction.NavigateToProductList("id"), categoriesAction.awaitItem())
        }
    }
}