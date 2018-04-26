package com.kawaiistudios.warframecompanion.container.home.news

interface INewsView {

    fun addEvent(event: News.Event)
    fun showToast(text: String)
}