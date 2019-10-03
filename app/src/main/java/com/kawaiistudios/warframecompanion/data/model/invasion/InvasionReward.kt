package com.kawaiistudios.warframecompanion.data.model.invasion

/**
 * Represents a reward for awarded for completing an invasion.
 */
data class InvasionReward(
        val countedItems: ArrayList<InvasionRewardItem>,
        val credits: Int,
        val asString: String,
        val itemString: String,
        val thumbnail: String
)