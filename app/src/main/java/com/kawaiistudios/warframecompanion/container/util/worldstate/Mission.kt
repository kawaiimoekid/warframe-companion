package com.kawaiistudios.warframecompanion.container.util.worldstate


data class Mission(
    val node: String,
    val type: String,
    val faction: String,
    val reward: Reward,
    val minEnemyLevel: Int,
    val maxEnemyLevel: Int,
    val nightmare: Boolean,
    val archwingRequired: Boolean
)