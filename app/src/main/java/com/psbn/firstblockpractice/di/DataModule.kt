package com.psbn.firstblockpractice.di

import com.psbn.firstblockpractice.data.repository.EventRepositoryImpl
import com.psbn.firstblockpractice.data.repository.FriendRepositoryImpl
import com.psbn.firstblockpractice.data.repository.SearchRepositoryImpl
import com.psbn.firstblockpractice.domain.news.repository.EventRepository
import com.psbn.firstblockpractice.domain.search.repository.SearchRepository
import com.psbn.firstblockpractice.domain.user.repository.FriendRepository
import com.psbn.firstblockpractice.help.data.CategoryRepositoryImpl
import com.psbn.firstblockpractice.help.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindEventRepository(eventRepository: EventRepositoryImpl): EventRepository

    @Binds
    fun bindFriendRepository(friendRepository: FriendRepositoryImpl): FriendRepository

    @Binds
    fun bindSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

}
