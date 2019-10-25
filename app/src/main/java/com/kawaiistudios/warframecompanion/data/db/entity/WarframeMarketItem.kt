package com.kawaiistudios.warframecompanion.data.db.entity

import androidx.room.Entity

@Entity(tableName = "warframeMarketItems")
data class WarframeMarketItem(
        val id: String,
        val urlName: String,
        val itemName: String
)