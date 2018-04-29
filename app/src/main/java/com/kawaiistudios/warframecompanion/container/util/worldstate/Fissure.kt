package com.kawaiistudios.warframecompanion.container.util.worldstate


data class Fissure(
    val id: String,
    val node: String,
    val missionType: String,
    val enemy: String,
    val tier: String,
    val tierNum: Int,
    val activation: String,
    val expiry: String,
    val expired: Boolean,
    val eta: String
)