package com.psbn.search.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchViewModelKey(val value: KClass<out ViewModel>)