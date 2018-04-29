package com.kawaiistudios.warframecompanion.container.util.worldstate


data class Alert(
    val id: String,
    val activation: String,
    val expiry: String,
    val mission: Mission,
    val expired: Boolean,
    val eta: String,
    val rewardTypes: List<String>
)