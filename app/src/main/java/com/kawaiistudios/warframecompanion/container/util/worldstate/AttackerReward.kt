package com.kawaiistudios.warframecompanion.container.util.worldstate


data class AttackerReward(
    val items: List<Any>,
    val countedItems: List<Any>,
    val credits: Int,
    val asString: String,
    val itemString: String,
    val thumbnail: String,
    val color: Int
)