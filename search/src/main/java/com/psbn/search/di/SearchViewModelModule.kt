package com.psbn.search.di

import androidx.lifecycle.ViewModel
import com.psbn.search.presentation.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchViewModelModule {

    @Binds
    @IntoMap
    @SearchViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}
