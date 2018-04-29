package com.kawaiistudios.warframecompanion.container.util.worldstate


data class DefenderReward(
    val items: List<Any>,
    val countedItems: List<CountedItem>,
    val credits: Int,
    val asString: String,
    val itemString: String,
    val thumbnail: String,
    val color: Int
)