package com.kawaiistudios.warframecompanion.data.repository

import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import com.kawaiistudios.warframecompanion.data.model.News
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    fun getNews(): Single<List<News>> = api.getNews()
            .map { news -> news.filter { !it.translations.en.isNullOrBlank() } }

    fun getTopNews(): Single<News> = api.getNews()
            .map { news -> news.filter { !it.translations.en.isNullOrBlank() } }
            .map { news -> news.maxBy { it.date } }

}