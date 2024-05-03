package com.ps_pn.firstblockpractice.di

import androidx.lifecycle.ViewModel
import com.ps_pn.firstblockpractice.presentation.fragments.help.HelpViewModel
import com.ps_pn.firstblockpractice.presentation.fragments.news.NewsViewModel
import com.ps_pn.firstblockpractice.presentation.fragments.search.SearchViewModel
import com.ps_pn.firstblockpractice.presentation.fragments.user.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HelpViewModel::class)
    fun bindHelpViewModel(helpViewModel: HelpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    fun bindFriendViewModel(userViewModel: UserProfileViewModel): ViewModel
}
