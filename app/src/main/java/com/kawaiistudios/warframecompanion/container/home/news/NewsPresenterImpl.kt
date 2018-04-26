package com.kawaiistudios.warframecompanion.container.home.news

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NewsPresenterImpl(
        private val mNewsView: INewsView
) {

    private val mRetrofit by lazy { Retrofit.Builder()
            .baseUrl("http://content.warframe.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val mApi by lazy { mRetrofit.create(IApi::class.java) }

    fun getNews() {
        val news = mApi.getNews()
        news.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>?, t: Throwable?) {
                mNewsView.showToast("We couldn't load news list")
            }

            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                response?.body()?.events?.sortedByDescending { it.date.date.longDate }?.forEach { ev ->
                    mNewsView.addEvent(ev)
                }
            }
        })
    }

    private interface IApi {

        @GET("dynamic/worldState.php")
        fun getNews(): Call<News>

    }
}