package com.kawaiistudios.warframecompanion.container.util.worldstate

interface WorldStateDownloaded {
    fun onSuccess(worldState: WorldState?)
    fun onFailure(t: Throwable?)
}