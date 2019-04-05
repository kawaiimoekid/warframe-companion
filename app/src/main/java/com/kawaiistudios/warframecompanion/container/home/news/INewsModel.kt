package com.kawaiistudios.warframecompanion.container.home.news

import com.kawaiistudios.warframecompanion.container.util.Platform
import com.kawaiistudios.warframecompanion.container.util.worldstate.News
import com.kawaiistudios.warframecompanion.container.util.worldstate.NewsDownloaded

interface INewsModel {

    fun getNews(platform: Platform): List<News>
    fun setOnNewsDownloadedCallback(callback: NewsDownloaded)

}