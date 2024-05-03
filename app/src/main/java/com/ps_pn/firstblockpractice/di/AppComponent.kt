package com.ps_pn.firstblockpractice.di

import android.app.Application
import com.ps_pn.firstblockpractice.presentation.MainActivity
import com.ps_pn.firstblockpractice.presentation.help.ui.HelpFragment
import com.ps_pn.firstblockpractice.presentation.news.ui.NewsFragment
import com.ps_pn.firstblockpractice.presentation.search.ui.EventsSearchFragment
import com.ps_pn.firstblockpractice.presentation.search.ui.OrgSearchFragment
import com.ps_pn.firstblockpractice.presentation.search.ui.SearchFragment
import com.ps_pn.firstblockpractice.presentation.user.ui.UserProfileFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(helpFragment: HelpFragment)
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
