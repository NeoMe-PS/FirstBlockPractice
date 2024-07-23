package com.psbn.firstblockpractice.help.presentation.viewModel

import app.cash.turbine.test
import com.psbn.firstblockpractice.help.domain.entity.Category
import com.psbn.firstblockpractice.help.domain.usecase.LoadCategoriesUseCase
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HelpViewModelTest {

    @Mock
    private lateinit var loadCategoriesUseCase: LoadCategoriesUseCase
    private lateinit var viewModel: HelpViewModel

    @Test
    fun shouldReturnStateWithResult() {
        runTest {
            doReturn(flowOf(listOf<Category>())).`when`(loadCategoriesUseCase).invoke()
            viewModel = HelpViewModel(loadCategoriesUseCase)
            val expected = HelpUIState.Response(listOf())

            viewModel.uiState.test {
                TestCase.assertEquals(expected, viewModel.uiState.value)
                cancelAndIgnoreRemainingEvents()
                awaitItem()
            }
            verify(loadCategoriesUseCase).invoke()
        }
    }
}