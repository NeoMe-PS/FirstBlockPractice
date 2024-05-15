package com.psbn.firstblockpractice.core

import android.app.Application
import com.psbn.firstblockpractice.di.AppComponent
import com.psbn.firstblockpractice.di.DaggerAppComponent
import com.psbn.firstblockpractice.help.di.HelpDeps
import com.psbn.firstblockpractice.help.di.HelpDepsProvider

class App : Application(), HelpDepsProvider {

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

    override fun getHelpDeps(): HelpDeps {
        return component
    }

}
