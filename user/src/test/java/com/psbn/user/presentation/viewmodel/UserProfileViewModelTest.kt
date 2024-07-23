package com.psbn.user.presentation.viewmodel

import app.cash.turbine.test
import com.psbn.user.domain.entity.Friend
import com.psbn.user.domain.usecase.LoadFriendsUseCase
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
class UserProfileViewModelTest {

    @Mock
    private lateinit var loadFriendsUseCase: LoadFriendsUseCase
    private lateinit var viewModel: UserProfileViewModel

    @Test
    fun shouldReturnStateWithResult() {
        runTest {
            doReturn(flowOf(listOf<Friend>())).`when`(loadFriendsUseCase).invoke()
            viewModel = UserProfileViewModel(loadFriendsUseCase)
            val expected = UserProfileState.Response(listOf())

            viewModel.uiState.test {
                assertEquals(expected, viewModel.uiState.value)
                cancelAndIgnoreRemainingEvents()
                awaitItem()
            }
            verify(loadFriendsUseCase).invoke()
        }
    }
}