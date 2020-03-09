package com.kawaiistudios.warframecompanion.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warframeMarketItems")
data class WarframeMarketItem(
        @PrimaryKey
        val id: String,
        val urlName: String,
        val itemName: String,
        val isObserved: Boolean
)