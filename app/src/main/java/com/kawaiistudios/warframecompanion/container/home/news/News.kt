package com.kawaiistudios.warframecompanion.container.home.news

import com.google.gson.annotations.SerializedName

class News(
        @SerializedName("Events")
        val events: List<Event>? = null
) {
    inner class Event(
            @SerializedName("Messages")
            val messages: List<Message>? = null,
            @SerializedName("Prop")
            val prop: String = "",
            @SerializedName("ImageUrl")
            val imageUrl: String = "",
            @SerializedName("Date")
            val date: Date
    ) {
        inner class Message(
                @SerializedName("LanguageCode")
                val languageCode: String = "",
                @SerializedName("Message")
                val message: String = ""
        )

        inner class Date(
                @SerializedName("\$date")
                val date: Date2
        ) {
            inner class Date2(
                    @SerializedName("\$numberLong")
                    val longDate: Long
            )
        }
    }
}