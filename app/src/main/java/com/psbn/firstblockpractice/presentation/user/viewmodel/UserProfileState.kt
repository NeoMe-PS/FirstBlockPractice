package com.psbn.firstblockpractice.presentation.user.viewmodel

import com.psbn.firstblockpractice.domain.user.entity.Friend

sealed class UserProfileState {
    object Loading : UserProfileState()
    object Error : UserProfileState()
    data class Response(val friends: List<Friend>) : UserProfileState()
}
