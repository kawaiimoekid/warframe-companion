package com.kawaiistudios.warframecompanion.data.web.market

import com.google.gson.annotations.SerializedName

data class WarframeMarketPayload<T>(
        @SerializedName("payload")
        val payload: T
)