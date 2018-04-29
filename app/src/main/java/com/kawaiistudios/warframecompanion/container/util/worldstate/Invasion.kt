package com.kawaiistudios.warframecompanion.container.util.worldstate


data class Invasion(
    val id: String,
    val node: String,
    val desc: String,
    val attackerReward: AttackerReward,
    val attackingFaction: String,
    val defenderReward: DefenderReward,
    val defendingFaction: String,
    val vsInfestation: Boolean,
    val activation: String,
    val count: Int,
    val requiredRuns: Int,
    val completion: Double,
    val completed: Boolean,
    val eta: String,
    val rewardTypes: List<String>
)