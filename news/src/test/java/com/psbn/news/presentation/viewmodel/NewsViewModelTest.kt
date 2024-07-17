package com.psbn.news.presentation.viewmodel

import app.cash.turbine.test
import com.psbn.news.domain.entity.Event
import com.psbn.news.domain.usecase.LoadEventsUseCase
import com.psbn.news.domain.usecase.ReadEventUseCase
import com.psbn.news.presentation.models.CategoryUI
import com.psbn.news.presentation.models.EventUI
import junit.framework.TestCase.assertEquals
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
class NewsViewModelTest {

    @Mock
    private lateinit var loadEventsUseCase: LoadEventsUseCase

    @Mock
    private lateinit var readEventUseCase: ReadEventUseCase
    private lateinit var viewModel: NewsViewModel

    @Test
    fun shouldReturnStateWithResult() {
        runTest {
            doReturn(flowOf(listOf<Event>())).`when`(loadEventsUseCase).invoke()
            viewModel = NewsViewModel(loadEventsUseCase, readEventUseCase)
            val expected = NewsUiState(isLoading = false, isError = false)

            viewModel.uiState.test {
                assertEquals(expected, viewModel.uiState.value)
                cancelAndIgnoreRemainingEvents()
            }
            verify(loadEventsUseCase).invoke()
        }
    }

    @Test
    fun shouldReturnEventWithIsReadFlag() {
        runTest {
            val expected = EventUI(
                id = 1,
                categories = listOf(CategoryUI(1, "asd", "asd")),
                label = "event.label",
                shortDesc = "event.shortDesc",
                fullDesc = "event.fullDesc",
                date = "event.date",
                dateStart = "event.dateStart",
                dateEnd = "event.dateEnd",
                thumbnail = 123,
                newsImages = listOf(3, 2),
                address = "event.address",
                phone = "event.phone",
                company = "event.company",
                diffInDays = "event.diffInDays",
                isRead = false
            )
            val id = 1

            doReturn(expected.copy(isRead = true)).`when`(readEventUseCase).invoke(id)
            doReturn(flowOf(listOf<Event>())).`when`(loadEventsUseCase).invoke()
            viewModel = NewsViewModel(loadEventsUseCase, readEventUseCase)
            viewModel.readEvent(id)

            assertEquals(expected, expected)
            verify(readEventUseCase).invoke(id)
        }
    }
}