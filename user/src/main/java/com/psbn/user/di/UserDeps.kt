package com.psbn.user.di

import com.psbn.firstblockpractice.core.data.network.ApiService

interface UserDeps {
    fun getApiService(): ApiService
}