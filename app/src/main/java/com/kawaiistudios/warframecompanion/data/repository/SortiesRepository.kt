package com.kawaiistudios.warframecompanion.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.kawaiistudios.warframecompanion.data.FetchCallback
import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.model.Sortie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SortiesRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    private val _sortie = MediatorLiveData<Sortie>()
    val sortie: LiveData<Sortie>
        get() = _sortie


    fun fetchSortie(callback: FetchCallback) {
        api.getSortie().enqueue(object : Callback<Sortie> {
            override fun onFailure(call: Call<Sortie>, t: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Sortie>, response: Response<Sortie>) {
                val sortie = response.body()
                if (sortie == null) {
                    callback.onFailure()
                } else {
                    callback.onSuccess()
                    _sortie.postValue(sortie)
                }
            }
        })
    }

}