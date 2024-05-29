package com.psbn.firstblockpractice.di

import android.app.Application
import com.psbn.firstblockpractice.help.di.HelpDeps
import com.psbn.firstblockpractice.presentation.MainActivity
import com.psbn.news.di.NewsDeps
import com.psbn.search.di.SearchDeps
import com.psbn.user.di.UserDeps
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataBaseModule::class, NetworkModule::class])
interface AppComponent : HelpDeps, NewsDeps, UserDeps, SearchDeps {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
