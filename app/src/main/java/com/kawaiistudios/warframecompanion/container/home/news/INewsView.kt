package com.kawaiistudios.warframecompanion.container.home.news

import com.kawaiistudios.warframecompanion.container.util.worldstate.News

interface INewsView {

    fun addNews(news: News)
    fun showToast(text: String)
}