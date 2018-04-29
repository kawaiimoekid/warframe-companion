package com.kawaiistudios.warframecompanion.container.util.worldstate



data class WorldState(
        val news: List<News>,
        val alerts: List<Alert>,
        val sortie: Sortie,
        val fissures: List<Fissure>,
        val invasions: List<Invasion>
)