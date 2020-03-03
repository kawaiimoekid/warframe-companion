package com.kawaiistudios.warframecompanion.data.web.market.resource

import com.google.gson.annotations.SerializedName

data class WarframeMarketOrdersResponse(
        @SerializedName("orders")
        val orders: List<WarframeMarketOrder>
)