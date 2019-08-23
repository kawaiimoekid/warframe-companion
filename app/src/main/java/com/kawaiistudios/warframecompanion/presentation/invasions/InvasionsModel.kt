package com.kawaiistudios.warframecompanion.presentation.invasions

data class InvasionsModel(
        val id: String,
        val node: String,
        val attackerIcon: Int,
        val attackerIconColor: Int,
        val attackerName: String,
        val attackerReward: String,
        val defenderIcon: Int,
        val defenderIconColor: Int,
        val defenderName: String,
        val defenderReward: String
)