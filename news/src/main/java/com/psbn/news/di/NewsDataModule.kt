package com.psbn.news.di

import com.psbn.news.data.repository.EventRepositoryImpl
import com.psbn.news.domain.repository.EventRepository
import dagger.Binds
import dagger.Module

@Module
interface NewsDataModule {
    @Binds
    fun bindNewsRepository(impl: EventRepositoryImpl): EventRepository
}


