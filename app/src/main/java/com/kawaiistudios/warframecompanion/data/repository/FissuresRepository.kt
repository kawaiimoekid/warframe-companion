package com.kawaiistudios.warframecompanion.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.kawaiistudios.warframecompanion.data.FetchCallback
import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FissuresRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    private val _fissures = MediatorLiveData<List<Fissure>>()
    val fissures: LiveData<List<Fissure>>
        get() = _fissures


    fun fetchFissures(callback: FetchCallback) {
        api.getFissures().enqueue(object : Callback<List<Fissure>> {
            override fun onFailure(call: Call<List<Fissure>>, t: Throwable) {
                callback.onFailure()
                _fissures.postValue(null)
            }

            override fun onResponse(call: Call<List<Fissure>>, response: Response<List<Fissure>>) {
                callback.onSuccess()
                response.body()
                        ?.filter { it.active && !it.expired }
                        ?.sortedWith(compareBy({ it.tierNum }, { it.expiry }))
                        ?.let { _fissures.postValue(it) }
            }
        })
    }

    fun getFissuresSingle() = api.getFissuresSingle()

}