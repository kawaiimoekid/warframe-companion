package com.kawaiistudios.warframecompanion.data.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class News(
        @SerializedName("id")
        val id: String,
        @SerializedName("link")
        val forumLink: String,
        @SerializedName("imageLink")
        val imageLink: String,
        @SerializedName("date")
        val date: DateTime,
        @SerializedName("translations")
        val translations: Translations
)