package com.kawaiistudios.warframecompanion.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.kawaiistudios.warframecompanion.data.FetchCallback
import com.kawaiistudios.warframecompanion.data.Resource
import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import com.kawaiistudios.warframecompanion.data.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    private val _news = MediatorLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news


    fun fetchNews(callback: FetchCallback) {
        api.getNews().enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                callback.onFailure()
                _news.postValue(null)
            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                callback.onSuccess()
                response.body()
                        ?.filter { !it.translations.en.isNullOrBlank() }
                        ?.sortedByDescending { it.date }
                        ?.let { _news.postValue(it) }
            }
        })
    }

    fun getNewsSingle() = api.getNewsSingle()

}