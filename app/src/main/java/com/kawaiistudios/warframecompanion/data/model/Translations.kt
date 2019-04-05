package com.kawaiistudios.warframecompanion.data.model

import com.google.gson.annotations.SerializedName

data class Translations(
        @SerializedName("en")
        val en: String?,
        @SerializedName("fr")
        val fr: String?,
        @SerializedName("it")
        val it: String?,
        @SerializedName("de")
        val de: String?,
        @SerializedName("es")
        val es: String?,
        @SerializedName("pt")
        val pt: String?,
        @SerializedName("ru")
        val ru: String?,
        @SerializedName("pl")
        val pl: String?,
        @SerializedName("tr")
        val tr: String?,
        @SerializedName("ja")
        val ja: String?,
        @SerializedName("zh")
        val zh: String?,
        @SerializedName("ko")
        val ko: String?,
        @SerializedName("tc")
        val tc: String?
)