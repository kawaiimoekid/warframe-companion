package com.kawaiistudios.warframecompanion.util.injection

import com.kawaiistudios.warframecompanion.presentation.fissures.FissuresView
import com.kawaiistudios.warframecompanion.presentation.home.HomeView
import com.kawaiistudios.warframecompanion.presentation.news.NewsView
import com.kawaiistudios.warframecompanion.presentation.sortie.SortieView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeView(): HomeView

    @ContributesAndroidInjector
    abstract fun contributeNewsView(): NewsView

    @ContributesAndroidInjector
    abstract fun contributeFissuresView(): FissuresView

    @ContributesAndroidInjector
    abstract fun contributeSortieView(): SortieView

}