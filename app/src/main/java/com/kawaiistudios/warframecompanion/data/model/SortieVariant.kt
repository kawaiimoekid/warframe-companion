package com.kawaiistudios.warframecompanion.data.model

import com.google.gson.annotations.SerializedName

data class SortieVariant(
        @SerializedName("missionType")
        val missionType: String,
        @SerializedName("modifier")
        val modifier: String,
        @SerializedName("modifierDescription")
        val modifierDescription: String,
        @SerializedName("node")
        val node: String
)