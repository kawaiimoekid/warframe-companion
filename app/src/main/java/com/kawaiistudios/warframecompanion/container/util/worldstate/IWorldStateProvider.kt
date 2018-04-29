package com.kawaiistudios.warframecompanion.container.util.worldstate

import com.kawaiistudios.warframecompanion.container.util.Platform

interface IWorldStateProvider {

    fun getWorldState(platform: Platform): WorldState
    fun downloadWorldState(platform: Platform)
    fun isDownloading(): Boolean
    fun isDownloaded(platform: Platform): Boolean
    fun setOnWorldStateDownloadedCallback(callback: WorldStateDownloaded)
    fun setOnNewsDownloadedCallback(callback: NewsDownloaded)
}