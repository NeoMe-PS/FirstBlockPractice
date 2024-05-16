package com.psbn.user.di

import com.psbn.user.data.repository.FriendRepositoryImpl
import com.psbn.user.domain.repository.FriendRepository
import dagger.Binds
import dagger.Module

@Module
interface UserDataModule {

    @Binds
    fun bindFriendRepository(impl: FriendRepositoryImpl): FriendRepository
}