package com.psbn.firstblockpractice.core.data.network

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory(private val application: Application) {

    companion object {
        private const val BASE_URL = "https://d0a6-85-112-35-173.ngrok-free.app"
    }

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(NetworkConnectionInterceptor(application))
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
