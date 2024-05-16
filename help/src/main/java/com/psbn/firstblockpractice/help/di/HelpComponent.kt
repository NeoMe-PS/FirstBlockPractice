package com.psbn.firstblockpractice.help.di

import com.psbn.firstblockpractice.help.presentation.ui.HelpFragment
import dagger.Component
import dagger.Component.Factory

@HelpScope
@Component(
    modules = [HelpDataModule::class, HelpViewModelModule::class],
    dependencies = [HelpDeps::class]
)
interface HelpComponent {

    fun inject(helpFragment: HelpFragment)

    @Factory
    interface HelpComponentFactory {
        fun create(helpDeps: HelpDeps): HelpComponent
    }
}