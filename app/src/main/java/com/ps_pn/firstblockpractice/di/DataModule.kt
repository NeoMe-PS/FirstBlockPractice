package com.ps_pn.firstblockpractice.di

import android.app.Application
import com.ps_pn.firstblockpractice.data.db.AppDao
import com.ps_pn.firstblockpractice.data.db.AppDataBase
import com.ps_pn.firstblockpractice.data.network.ApiFactory
import com.ps_pn.firstblockpractice.data.network.ApiService
import com.ps_pn.firstblockpractice.data.repositoy.CategoryRepositoryImpl
import com.ps_pn.firstblockpractice.data.repositoy.EventRepositoryImpl
import com.ps_pn.firstblockpractice.data.repositoy.FriendRepositoryImpl
import com.ps_pn.firstblockpractice.data.repositoy.SearchRepositoryImpl
import com.ps_pn.firstblockpractice.domain.repository.CategoryRepository
import com.ps_pn.firstblockpractice.domain.repository.EventRepository
import com.ps_pn.firstblockpractice.domain.repository.FriendRepository
import com.ps_pn.firstblockpractice.domain.repository.SearchRepository
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
