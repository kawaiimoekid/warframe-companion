package com.kawaiistudios.warframecompanion.container.util.worldstate

import java.util.*


data class News(
        val id: String,
        val message: String,
        val link: String,
        val imageLink: String,
        val priority: Boolean,
        val date: Date,
        val eta: String,
        val update: Boolean,
        val primeAccess: Boolean,
        val stream: Boolean,
        val asString: String
)