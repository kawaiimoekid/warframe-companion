package com.kawaiistudios.warframecompanion.data.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class Sortie(
        @SerializedName("id")
        val id: String,
        @SerializedName("expiry")
        val expiry: DateTime,
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("expired")
        val expired: Boolean,
        @SerializedName("boss")
        val boss: String,
        @SerializedName("faction")
        val faction: String,
        @SerializedName("variants")
        val variants: List<SortieVariant>
)