package com.kawaiistudios.warframecompanion.data.web.market.resource

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class WarframeMarketOrderUser(
        @SerializedName("id")
        val id: String,
        @SerializedName("ingame_name")
        val name: String,
        @SerializedName("status")
        val status: UserStatus,
        @SerializedName("avatar")
        val avatar: String,
        @SerializedName("region")
        val region: String,
        @SerializedName("reputation")
        val reputation: Int,
        @SerializedName("reputation_bonus")
        val reputationBonus: Int,
        @SerializedName("last_seen")
        val lastSeen: DateTime
)