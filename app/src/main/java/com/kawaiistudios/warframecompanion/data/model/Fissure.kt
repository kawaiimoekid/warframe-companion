package com.kawaiistudios.warframecompanion.data.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class Fissure(
        @SerializedName("id")
        val id: String,
        @SerializedName("expiry")
        val expiry: DateTime,
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("expired")
        val expired: Boolean,
        @SerializedName("node")
        val node: String,
        @SerializedName("missionType")
        val missionType: String,
        @SerializedName("enemy")
        val enemy: String,
        @SerializedName("tier")
        val tier: String,
        @SerializedName("tierNum")
        val tierNum: Int
)