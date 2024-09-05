package com.meli.feature.search.presentation

import app.cash.turbine.turbineScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchViewModelTest {

    @Test
    fun `onCancelDialogClick Should emit OnBackAction`() = runTest {
        turbineScope {

            // Given
            val viewModel = SearchViewModel()
            val searchAction = viewModel.searchAction.testIn(backgroundScope)

            // When
            viewModel.onSearchClick("query")


            // Then
            assertEquals(SearchAction.NavigateToProductList("query"), searchAction.awaitItem())
        }
    }
}