package com.kawaiistudios.warframecompanion.di

import com.kawaiistudios.warframecompanion.presentation.codex.CodexView
import com.kawaiistudios.warframecompanion.presentation.dashboard.DashboardView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.fissures.DashboardFissuresWidgetView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.invasions.DashboardInvasionsWidgetView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.news.DashboardNewsWidgetView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.sortie.DashboardSortieWidgetView
import com.kawaiistudios.warframecompanion.presentation.fissures.FissuresView
import com.kawaiistudios.warframecompanion.presentation.invasions.InvasionsView
import com.kawaiistudios.warframecompanion.presentation.market.dashboard.MarketDashboardView
import com.kawaiistudios.warframecompanion.presentation.market.item.MarketItemView
import com.kawaiistudios.warframecompanion.presentation.market.item.buys.MarketItemBuysView
import com.kawaiistudios.warframecompanion.presentation.market.item.details.MarketItemDetailsView
import com.kawaiistudios.warframecompanion.presentation.market.item.sales.MarketItemSalesView
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
    abstract fun contributeDashboardSortieWidgetView(): DashboardSortieWidgetView

    @ContributesAndroidInjector
    abstract fun contributeNewsView(): NewsView

    @ContributesAndroidInjector
    abstract fun contributeFissuresView(): FissuresView

    @ContributesAndroidInjector
    abstract fun contributeInvasionsView(): InvasionsView

    @ContributesAndroidInjector
    abstract fun contributeSortieView(): SortieView

    @ContributesAndroidInjector
    abstract fun contributeMarketDashboardView(): MarketDashboardView

    @ContributesAndroidInjector
    abstract fun contributeMarketItemView(): MarketItemView

    @ContributesAndroidInjector
    abstract fun contributeMarketItemDetailsView(): MarketItemDetailsView

    @ContributesAndroidInjector
    abstract fun contributeMarketItemSalesView(): MarketItemSalesView

    @ContributesAndroidInjector
    abstract fun contributeMarketItemBuysView(): MarketItemBuysView

    @ContributesAndroidInjector
    abstract fun contributeCodexView(): CodexView

}