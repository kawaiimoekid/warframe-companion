package com.kawaiistudios.warframecompanion.data.web.market.resource

import com.google.gson.annotations.SerializedName
import com.kawaiistudios.warframecompanion.data.db.entity.WarframeMarketItem

data class WarframeMarketItem(
        @SerializedName("id")
        val id: String,
        @SerializedName("url_name")
        val urlName: String,
        @SerializedName("item_name")
        val itemName: String
) {

        fun toEntity() = WarframeMarketItem(
                id,
                urlName,
                itemName,
                false
        )

}