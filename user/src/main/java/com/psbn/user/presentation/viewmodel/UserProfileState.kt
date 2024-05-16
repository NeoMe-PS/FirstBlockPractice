package com.psbn.user.presentation.viewmodel

import com.psbn.user.domain.entity.Friend

sealed class UserProfileState {
    object Loading : UserProfileState()
    object Error : UserProfileState()
    data class Response(val friends: List<Friend>) : UserProfileState()
}
