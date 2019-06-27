package com.kawaiistudios.warframecompanion.di

import com.kawaiistudios.warframecompanion.presentation.dashboard.DashboardView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.fissures.DashboardFissuresWidgetView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.invasions.DashboardInvasionsWidgetView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.news.DashboardNewsWidgetView
import com.kawaiistudios.warframecompanion.presentation.fissures.FissuresView
import com.kawaiistudios.warframecompanion.presentation.news.NewsView
import com.kawaiistudios.warframecompanion.presentation.sortie.SortieView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardView(): DashboardView

    @ContributesAndroidInjector
    abstract fun contributeDashboardNewsWidgetView(): DashboardNewsWidgetView

    @ContributesAndroidInjector
    abstract fun contributeDashboardFissuresWidgetView(): DashboardFissuresWidgetView

    @ContributesAndroidInjector
    abstract fun contributeDashboardInvasionsWidgetView(): DashboardInvasionsWidgetView

    @ContributesAndroidInjector
    abstract fun contributeNewsView(): NewsView

    @ContributesAndroidInjector
    abstract fun contributeFissuresView(): FissuresView

    @ContributesAndroidInjector
    abstract fun contributeSortieView(): SortieView

}