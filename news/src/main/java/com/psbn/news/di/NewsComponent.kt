package com.psbn.news.di

import android.app.Application
import com.psbn.news.presentation.ui.FilterFragment
import com.psbn.news.presentation.ui.NewsDetailFragment
import com.psbn.news.presentation.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Factory

@NewsScope
@Component(
    modules = [NewsDataModule::class, NewsViewModelModule::class],
    dependencies = [NewsDeps::class]
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)
    fun inject(newsDetailFragment: NewsDetailFragment)
    fun inject(filterFragment: FilterFragment)

    @Factory
    interface NewsFactory {
        fun create(@BindsInstance application: Application, newsDeps: NewsDeps): NewsComponent
    }
}