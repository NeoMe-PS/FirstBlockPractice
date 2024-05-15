package com.psbn.firstblockpractice.di

import android.app.Application
import com.psbn.firstblockpractice.core.data.db.AppDao
import com.psbn.firstblockpractice.core.data.db.AppDataBase
import dagger.Module
import dagger.Provides


@Module
class DataBaseModule {
    @Provides
    fun provideAppDao(application: Application): AppDao {
        return AppDataBase.getInstance(application).appDao()
    }
}