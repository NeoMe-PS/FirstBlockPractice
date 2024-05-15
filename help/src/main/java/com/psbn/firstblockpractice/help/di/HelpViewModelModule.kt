package com.psbn.firstblockpractice.help.di

import androidx.lifecycle.ViewModel
import com.psbn.firstblockpractice.help.presentation.viewModel.HelpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface HelpViewModelModule {

    @Binds
    @IntoMap
    @HelpViewModelKey(HelpViewModel::class)
    fun bindNewsViewModel(helpViewModel: HelpViewModel): ViewModel
}