package com.psbn.user.di

import androidx.lifecycle.ViewModel
import com.psbn.user.presentation.viewmodel.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserViewModelModule {

    @Binds
    @IntoMap
    @UserViewModelKey(UserProfileViewModel::class)
    fun bindFriendViewModel(userViewModel: UserProfileViewModel): ViewModel
}
