package com.kawaiistudios.warframecompanion.presentation.sortie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.data.FetchCallback
import com.kawaiistudios.warframecompanion.data.model.Sortie
import com.kawaiistudios.warframecompanion.data.repository.SortiesRepository
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import javax.inject.Inject

class SortieViewModel @Inject constructor(
        private val repository: SortiesRepository
) : ViewModel(), FetchCallback {

    companion object {
        private const val FACTION_GRINEER = "Grineer"
        private const val FACTION_CORPUS = "Corpus"
        private const val FACTION_INFESTED = "Infestation"
    }

    val sortie = Transformations.map(repository.sortie, ::mapSortie)

    private val _showSortie = MutableLiveData<Boolean>()
    val showSortie: LiveData<Boolean> get() = _showSortie

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private val _showFailure = MutableLiveData<Boolean>()
    val showFailure: LiveData<Boolean> get() = _showFailure


    init {
        _showSortie.value = false
        _showFailure.value = false
        _showLoading.value = true
        repository.fetchSortie(this)
    }

    fun refresh() {
        _showSortie.value = false
        _showFailure.value = false
        _showLoading.value = true
        repository.fetchSortie(this)
    }

    private fun mapSortie(resource: Sortie): SortieModel {
        return SortieModel(
                resource.id,
                expiryInMillis(resource.expiry),
                resource.boss,
                resource.faction,
                getResourceForFaction(resource.faction),
                resource.variants.map {
                    SortieVariantModel(
                            it.missionType,
                            it.modifier,
                            it.modifierDescription,
                            it.node
                    )
                }
        )
    }

    private fun expiryInMillis(dateTime: DateTime): Long {
        val difference = Duration(DateTime.now(DateTimeZone.UTC), dateTime)
        return difference.millis
    }

    private fun getResourceForFaction(faction: String): Int {
        return when (faction) {
            FACTION_GRINEER -> R.drawable.ic_grineer
            FACTION_CORPUS -> R.drawable.ic_corpus
            FACTION_INFESTED -> R.drawable.ic_infested
            else -> R.drawable.ic_error_black_24dp
        }
    }

    override fun onFailure() {
        _showSortie.postValue(false)
        _showLoading.postValue(false)
        _showFailure.postValue(true)
    }

    override fun onSuccess() {
        _showSortie.postValue(true)
        _showLoading.postValue(false)
        _showFailure.postValue(false)
    }

}