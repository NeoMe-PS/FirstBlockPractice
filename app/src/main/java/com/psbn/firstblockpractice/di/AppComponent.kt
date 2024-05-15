package com.psbn.firstblockpractice.di

import android.app.Application
import com.psbn.firstblockpractice.help.di.HelpDeps
import com.psbn.firstblockpractice.presentation.MainActivity
import com.psbn.firstblockpractice.presentation.news.ui.NewsFragment
import com.psbn.firstblockpractice.presentation.search.ui.EventsSearchFragment
import com.psbn.firstblockpractice.presentation.search.ui.OrgSearchFragment
import com.psbn.firstblockpractice.presentation.search.ui.SearchFragment
import com.psbn.firstblockpractice.presentation.user.ui.UserProfileFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, DataBaseModule::class, NetworkModule::class])
interface AppComponent : HelpDeps {


    fun inject(mainActivity: MainActivity)
    fun inject(newsFragment: NewsFragment)
    fun inject(eventsSearchFragment: EventsSearchFragment)
    fun inject(orgSearchFragment: OrgSearchFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(userProfileFragment: UserProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
