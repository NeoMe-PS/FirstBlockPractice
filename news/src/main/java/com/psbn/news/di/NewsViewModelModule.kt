package com.psbn.news.di

import androidx.lifecycle.ViewModel
import com.psbn.news.presentation.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NewsViewModelModule {

    @Binds
    @IntoMap
    @NewsViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(newsForComposeViewModel: NewsViewModel): ViewModel
}
