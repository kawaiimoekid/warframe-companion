package com.kawaiistudios.warframecompanion.container.util.worldstate


data class Sortie(
    val id: String,
    val activation: String,
    val expiry: String,
    val rewardPool: String,
    val variants: List<Variant>,
    val boss: String,
    val faction: String,
    val expired: Boolean,
    val eta: String
)