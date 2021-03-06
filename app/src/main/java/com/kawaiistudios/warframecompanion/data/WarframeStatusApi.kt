package com.kawaiistudios.warframecompanion.data

import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.model.News
import com.kawaiistudios.warframecompanion.data.model.Sortie
import retrofit2.Call
import retrofit2.http.GET

interface WarframeStatusApi {

    @GET("pc/news")
    fun getNews(): Call<List<News>>

    @GET("pc/fissures")
    fun getFissures(): Call<List<Fissure>>

    @GET("pc/sortie")
    fun getSortie(): Call<Sortie>

}