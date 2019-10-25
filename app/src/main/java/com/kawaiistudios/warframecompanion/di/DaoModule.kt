package com.kawaiistudios.warframecompanion.di

import com.kawaiistudios.warframecompanion.data.db.Database
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @Provides
    fun provideWarframeMarketItemDao(db: Database) = db.warframeMarketItemDao()

}