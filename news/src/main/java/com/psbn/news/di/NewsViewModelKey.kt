package com.psbn.news.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class NewsViewModelKey(val value: KClass<out ViewModel>)