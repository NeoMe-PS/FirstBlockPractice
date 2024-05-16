package com.psbn.search.di

import com.psbn.search.data.repository.SearchRepositoryImpl
import com.psbn.search.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module

@Module
interface SearchDataModule {
    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}