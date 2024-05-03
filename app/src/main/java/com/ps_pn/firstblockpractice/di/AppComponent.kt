package com.ps_pn.firstblockpractice.di

import android.app.Application
import com.ps_pn.firstblockpractice.presentation.MainActivity
import com.ps_pn.firstblockpractice.presentation.fragments.help.HelpFragment
import com.ps_pn.firstblockpractice.presentation.fragments.news.NewsFragment
import com.ps_pn.firstblockpractice.presentation.fragments.search.EventsSearchFragment
import com.ps_pn.firstblockpractice.presentation.fragments.search.OrgSearchFragment
import com.ps_pn.firstblockpractice.presentation.fragments.search.SearchFragment
import com.ps_pn.firstblockpractice.presentation.fragments.user.UserProfileFragment
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
