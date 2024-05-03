package com.ps_pn.firstblockpractice.di

import androidx.lifecycle.ViewModel
import com.ps_pn.firstblockpractice.presentation.help.viewModel.HelpViewModel
import com.ps_pn.firstblockpractice.presentation.news.viewmodel.NewsViewModel
import com.ps_pn.firstblockpractice.presentation.search.viewmodel.SearchViewModel
import com.ps_pn.firstblockpractice.presentation.user.viewmodel.UserProfileViewModel
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
