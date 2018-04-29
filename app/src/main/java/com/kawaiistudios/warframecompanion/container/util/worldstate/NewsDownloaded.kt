package com.kawaiistudios.warframecompanion.container.util.worldstate

interface NewsDownloaded {
    fun onSuccess(news: List<News>?)
    fun onFailure(t: Throwable?)
}