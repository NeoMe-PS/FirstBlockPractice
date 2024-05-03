package com.ps_pn.firstblockpractice.presentation.user.viewmodel

import com.ps_pn.firstblockpractice.domain.user.entity.Friend

sealed class UserProfileState {
    object Loading : UserProfileState()
    object Error : UserProfileState()
    data class Response(val friends: List<Friend>) : UserProfileState()
}
