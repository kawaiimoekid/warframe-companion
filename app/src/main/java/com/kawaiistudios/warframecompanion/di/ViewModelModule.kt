package com.kawaiistudios.warframecompanion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kawaiistudios.warframecompanion.presentation.codex.CodexViewModel
import com.kawaiistudios.warframecompanion.presentation.dashboard.DashboardViewModel
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.fissures.DashboardFissuresWidgetViewModel
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.invasions.DashboardInvasionsWidgetViewModel
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.news.DashboardNewsWidgetViewModel
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.sortie.DashboardSortieWidgetViewModel
import com.kawaiistudios.warframecompanion.presentation.fissures.FissuresViewModel
import com.kawaiistudios.warframecompanion.presentation.invasions.InvasionsViewModel
import com.kawaiistudios.warframecompanion.presentation.market.dashboard.MarketDashboardViewModel
import com.kawaiistudios.warframecompanion.presentation.market.item.MarketItemViewModel
import com.kawaiistudios.warframecompanion.presentation.market.item.buys.MarketItemBuysViewModel
import com.kawaiistudios.warframecompanion.presentation.market.item.details.MarketItemDetailsViewModel
import com.kawaiistudios.warframecompanion.presentation.market.item.sales.MarketItemSalesViewModel
import com.kawaiistudios.warframecompanion.presentation.news.NewsViewModel
import com.kawaiistudios.warframecompanion.presentation.sortie.SortieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboard(vm: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardNewsWidgetViewModel::class)
    abstract fun bindDashboardNewsWidget(vm: DashboardNewsWidgetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardFissuresWidgetViewModel::class)
    abstract fun bindDashboardFissuresWidget(vm: DashboardFissuresWidgetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardInvasionsWidgetViewModel::class)
    abstract fun bindDashboardInvasionsWidget(vm: DashboardInvasionsWidgetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardSortieWidgetViewModel::class)
    abstract fun bindDashboardSortieWidget(vm: DashboardSortieWidgetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNews(vm: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FissuresViewModel::class)
    abstract fun bindFissures(vm: FissuresViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InvasionsViewModel::class)
    abstract fun bindInvasions(vm: InvasionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SortieViewModel::class)
    abstract fun bindSortie(vm: SortieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketDashboardViewModel::class)
    abstract fun bindMarketDashboard(vm: MarketDashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketItemViewModel::class)
    abstract fun bindMarketItem(vm: MarketItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketItemDetailsViewModel::class)
    abstract fun bindMarketItemDetails(vm: MarketItemDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketItemSalesViewModel::class)
    abstract fun bindMarketItemSales(vm: MarketItemSalesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketItemBuysViewModel::class)
    abstract fun bindMarketItemBuys(vm: MarketItemBuysViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CodexViewModel::class)
    abstract fun bindCodex(vm: CodexViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}