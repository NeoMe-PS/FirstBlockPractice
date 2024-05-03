package com.ps_pn.firstblockpractice.presentation.user.viewmodel

import androidx.lifecycle.ViewModel
import com.ps_pn.firstblockpractice.domain.user.usecase.LoadFriendsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val loadFriendsUseCase: LoadFriendsUseCase
) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var _uiState = MutableStateFlow<UserProfileState>(UserProfileState.Loading)
    val uiState: StateFlow<UserProfileState>
        get() = _uiState.asStateFlow()

    init {
        setState()
    }

    private fun setState() {
        scope.launch {
            loadFriendsUseCase.invoke()
                .map { data -> UserProfileState.Response(data) as UserProfileState }
                .onStart { emit(UserProfileState.Loading) }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
