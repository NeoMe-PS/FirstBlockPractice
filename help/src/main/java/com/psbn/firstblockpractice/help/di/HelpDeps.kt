package com.psbn.firstblockpractice.help.di

import com.psbn.firstblockpractice.core.data.db.AppDao
import com.psbn.firstblockpractice.core.data.jsonstorage.JSONParser
import com.psbn.firstblockpractice.core.data.network.ApiService

interface HelpDeps {

    fun getDataBase(): AppDao
    fun getApiService(): ApiService
    fun getJSONParcer(): JSONParser
}