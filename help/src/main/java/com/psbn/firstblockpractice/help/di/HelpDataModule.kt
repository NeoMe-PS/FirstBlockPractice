package com.psbn.firstblockpractice.help.di

import com.psbn.firstblockpractice.help.data.CategoryRepositoryImpl
import com.psbn.firstblockpractice.help.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface HelpDataModule {

    @Binds
    fun bindRepository(impl: CategoryRepositoryImpl): CategoryRepository

}