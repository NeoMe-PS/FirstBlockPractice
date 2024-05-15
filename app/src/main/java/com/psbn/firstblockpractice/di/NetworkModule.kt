package com.psbn.firstblockpractice.di

import android.app.Application
import com.psbn.firstblockpractice.core.data.network.ApiFactory
import com.psbn.firstblockpractice.core.data.network.ApiService
import dagger.Module
import dagger.Provides


@Module
class NetworkModule {
    @Provides
    fun provideApiService(application: Application): ApiService {
        return ApiFactory(application).apiService
    }
}