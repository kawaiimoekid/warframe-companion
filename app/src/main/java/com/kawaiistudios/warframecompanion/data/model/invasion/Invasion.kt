package com.kawaiistudios.warframecompanion.data.model.invasion

import org.joda.time.DateTime

/**
 * Represents a faction vs faction invasion with rewards
 * after completing same mission 3 times.
 */
data class Invasion(
        val id: String,
        val activation: DateTime,
        val node: String,
        val desc: String,
        val attackerReward: InvasionReward,
        val attackingFaction: String,
        val defenderReward: InvasionReward,
        val defendingFaction: String,
        val vsInfestation: Boolean,
        val completion: Double,
        val completed: Boolean
)