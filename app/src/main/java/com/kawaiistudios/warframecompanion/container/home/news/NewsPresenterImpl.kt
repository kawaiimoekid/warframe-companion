package com.kawaiistudios.warframecompanion.container.home.news

import com.kawaiistudios.warframecompanion.container.util.Platform
import com.kawaiistudios.warframecompanion.container.util.worldstate.News
import com.kawaiistudios.warframecompanion.container.util.worldstate.NewsDownloaded

class NewsPresenterImpl(
        private val mView: INewsView
) {

    private val mModel: INewsModel by lazy { NewsModelImpl() }
    private val mCallback: NewsDownloaded by lazy {
        object : NewsDownloaded {
            override fun onSuccess(news: List<News>?) {
                news?.sortedByDescending { it.date }?.forEach { mView.addNews(it) }
            }

            override fun onFailure(t: Throwable?) {
                t?.printStackTrace()
            }
        }
    }

    fun getNews() {
        try {
            val news = mModel.getNews(Platform.Pc)
            news.sortedByDescending { it.date }.forEach { mView.addNews(it) }
        } catch (e: Exception) {
            mModel.setOnNewsDownloadedCallback(mCallback)
        }
    }
}