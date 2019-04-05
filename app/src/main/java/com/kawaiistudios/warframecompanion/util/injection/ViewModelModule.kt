package com.kawaiistudios.warframecompanion.util.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kawaiistudios.warframecompanion.presentation.fissures.FissuresViewModel
import com.kawaiistudios.warframecompanion.presentation.home.HomeViewModel
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
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHome(vm: HomeViewModel): ViewModel

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
    @ViewModelKey(SortieViewModel::class)
    abstract fun bindSortie(vm: SortieViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}