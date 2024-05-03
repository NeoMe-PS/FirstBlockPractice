package com.ps_pn.firstblockpractice.presentation.fragments.user

import com.ps_pn.firstblockpractice.domain.entity.Friend

sealed class UserProfileState {
    object Loading : UserProfileState()
    object Error : UserProfileState()
    data class Response(val friends: List<Friend>) : UserProfileState()
}
