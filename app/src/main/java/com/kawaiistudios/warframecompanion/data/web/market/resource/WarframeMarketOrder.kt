package com.kawaiistudios.warframecompanion.data.web.market.resource

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class WarframeMarketOrder(
        @SerializedName("id")
        val id: String,
        @SerializedName("platinum")
        val platinum: Double,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("user")
        val user: WarframeMarketOrderUser,
        @SerializedName("order_type")
        val orderType: String,
        @SerializedName("platform")
        val platform: String,
        @SerializedName("region")
        val region: String,
        @SerializedName("creation_date")
        val creationDate: DateTime,
        @SerializedName("last_update")
        val lastUpdate: DateTime,
        @SerializedName("visible")
        val isVisible: Boolean
)