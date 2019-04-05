package com.kawaiistudios.warframecompanion.util.injection

import com.kawaiistudios.warframecompanion.presentation.container.ContainerView
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeContainer(): ContainerView

}