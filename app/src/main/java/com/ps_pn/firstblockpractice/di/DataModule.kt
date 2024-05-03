package com.ps_pn.firstblockpractice.di

import android.app.Application
import com.ps_pn.firstblockpractice.data.db.AppDao
import com.ps_pn.firstblockpractice.data.db.AppDataBase
import com.ps_pn.firstblockpractice.data.network.ApiFactory
import com.ps_pn.firstblockpractice.data.network.ApiService
import com.ps_pn.firstblockpractice.data.repository.CategoryRepositoryImpl
import com.ps_pn.firstblockpractice.data.repository.EventRepositoryImpl
import com.ps_pn.firstblockpractice.data.repository.FriendRepositoryImpl
import com.ps_pn.firstblockpractice.data.repository.SearchRepositoryImpl
import com.ps_pn.firstblockpractice.domain.help.repository.CategoryRepository
import com.ps_pn.firstblockpractice.domain.news.repository.EventRepository
import com.ps_pn.firstblockpractice.domain.search.repository.SearchRepository
import com.ps_pn.firstblockpractice.domain.user.repository.FriendRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

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

    companion object {
        @Provides
        fun provideAppDao(application: Application): AppDao {
            return AppDataBase.getInstance(application).appDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
