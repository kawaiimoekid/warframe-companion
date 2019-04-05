package com.kawaiistudios.warframecompanion.presentation.fissures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.data.FetchCallback
import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.repository.FissuresRepository
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import javax.inject.Inject

class FissuresViewModel @Inject constructor(
        private val repository: FissuresRepository
) : ViewModel(), FetchCallback {

    companion object {
        private const val TIER_LITH = 1
        private const val TIER_MESO = 2
        private const val TIER_NEO = 3
        private const val TIER_AXI = 4
    }

    val fissures = Transformations.map(repository.fissures, ::mapFissures)

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private val _showFailure = MutableLiveData<Boolean>()
    val showFailure: LiveData<Boolean> get() = _showFailure


    init {
        _showFailure.value = false
        _showLoading.value = true
        repository.fetchFissures(this)
    }

    fun refresh() {
        _showFailure.value = false
        _showLoading.value = true
        repository.fetchFissures(this)
    }

    private fun mapFissures(resource: List<Fissure>?): List<FissuresModel> {
        return resource?.map {
            FissuresModel(
                    it.id,
                    it.node,
                    it.missionType,
                    it.enemy,
                    expiryInMillis(it.expiry),
                    getResourceForTier(it.tierNum)
            )
        } ?: emptyList()
    }

    private fun expiryInMillis(dateTime: DateTime): Long {
        val difference = Duration(DateTime.now(DateTimeZone.UTC), dateTime)
        return difference.millis
    }

    private fun getResourceForTier(tier: Int): Int {
        return when (tier) {
            TIER_LITH -> R.drawable.ic_lith
            TIER_MESO -> R.drawable.ic_meso
            TIER_NEO -> R.drawable.ic_neo
            TIER_AXI -> R.drawable.ic_axi
            else -> R.drawable.ic_error_black_24dp
        }
    }

    override fun onFailure() {
        _showLoading.postValue(false)
        _showFailure.postValue(true)
    }

    override fun onSuccess() {
        _showLoading.postValue(false)
        _showFailure.postValue(false)
    }

}