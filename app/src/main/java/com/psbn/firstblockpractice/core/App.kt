package com.psbn.firstblockpractice.core

import android.app.Application
import com.psbn.firstblockpractice.di.AppComponent
import com.psbn.firstblockpractice.di.DaggerAppComponent
import com.psbn.firstblockpractice.help.di.HelpDeps
import com.psbn.firstblockpractice.help.di.HelpDepsProvider
import com.psbn.news.di.NewsDeps
import com.psbn.news.di.NewsDepsProvider
import com.psbn.search.di.SearchDeps
import com.psbn.search.di.SearchDepsProvider
import com.psbn.user.di.UserDeps
import com.psbn.user.di.UserDepsProvider

class App : Application(), HelpDepsProvider, NewsDepsProvider, UserDepsProvider,
    SearchDepsProvider {

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

    override fun getNewsDeps(): NewsDeps {
        return component
    }

    override fun getUserDeps(): UserDeps {
        return component
    }

    override fun getSearchDeps(): SearchDeps {
        return component
    }
}
