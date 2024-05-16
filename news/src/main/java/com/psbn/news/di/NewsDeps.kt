package com.psbn.news.di

import com.psbn.firstblockpractice.core.data.db.AppDao
import com.psbn.firstblockpractice.core.data.jsonstorage.JSONParser
import com.psbn.firstblockpractice.core.data.network.ApiService

interface NewsDeps {

    fun getDataBase(): AppDao
    fun getApiService(): ApiService
    fun getJSONParcer(): JSONParser
}
