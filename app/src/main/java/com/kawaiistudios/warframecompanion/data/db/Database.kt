package com.kawaiistudios.warframecompanion.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kawaiistudios.warframecompanion.data.db.dao.WarframeMarketItemDao
import com.kawaiistudios.warframecompanion.data.db.entity.WarframeMarketItem

@Database(entities = [
    WarframeMarketItem::class
], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun warframeMarketItemDao(): WarframeMarketItemDao

}