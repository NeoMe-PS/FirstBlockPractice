package com.ps_pn.firstblockpractice.presentation

import android.app.Application
import com.ps_pn.firstblockpractice.di.AppComponent
import com.ps_pn.firstblockpractice.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
