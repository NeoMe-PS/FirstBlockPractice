package com.psbn.search.di

import android.app.Application
import com.psbn.search.presentation.ui.EventsSearchFragment
import com.psbn.search.presentation.ui.OrgSearchFragment
import com.psbn.search.presentation.ui.SearchFragment
import dagger.BindsInstance
import dagger.Component

@SearchScope
@Component(
    modules = [SearchDataModule::class, SearchViewModelModule::class],
    dependencies = [SearchDeps::class]
)
interface SearchComponent {
    fun inject(searchFragment: SearchFragment)
    fun inject(orgSearchFragment: OrgSearchFragment)
    fun inject(eventsSearchFragment: EventsSearchFragment)

    @Component.Factory
    interface SearchFactory {
        fun create(@BindsInstance application: Application, searchDeps: SearchDeps): SearchComponent
    }
}