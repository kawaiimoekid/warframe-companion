package com.kawaiistudios.warframecompanion.data.web.market.resource

import com.google.gson.annotations.SerializedName

data class WarframeMarketItemsResponse(
        @SerializedName("items")
        val items: List<WarframeMarketItem>
)