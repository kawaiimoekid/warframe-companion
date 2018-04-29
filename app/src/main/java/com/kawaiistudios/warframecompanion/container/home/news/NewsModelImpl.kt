package com.kawaiistudios.warframecompanion.container.home.news

import com.kawaiistudios.warframecompanion.container.util.Platform
import com.kawaiistudios.warframecompanion.container.util.worldstate.*

class NewsModelImpl : INewsModel {

    private val mProvider: IWorldStateProvider by lazy { WarframestatWorldStateProvider }

    override fun getNews(platform: Platform): List<News> {
        try {
            return mProvider.getWorldState(platform).news
        } catch (exception: WorldStateException) {
            throw WorldStateException("Couldn't get world state")
        }
    }

    override fun setOnNewsDownloadedCallback(callback: NewsDownloaded) {
        mProvider.setOnNewsDownloadedCallback(callback)
    }

}