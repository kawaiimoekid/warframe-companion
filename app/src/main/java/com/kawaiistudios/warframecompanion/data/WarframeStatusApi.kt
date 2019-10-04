package com.kawaiistudios.warframecompanion.data

import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.model.News
import com.kawaiistudios.warframecompanion.data.model.Sortie
import com.kawaiistudios.warframecompanion.data.model.invasion.Invasion
import io.reactivex.Single
import retrofit2.http.GET

interface WarframeStatusApi {

    @GET("pc/news")
    fun getNews(): Single<List<News>>

    @GET("pc/fissures")
    fun getFissures(): Single<List<Fissure>>

    @GET("pc/invasions")
    fun getInvasions(): Single<List<Invasion>>

    @GET("pc/sortie")
    fun getSortie(): Single<Sortie>

}