package com.kawaiistudios.warframecompanion.presentation.fissures

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.repository.FissuresRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import javax.inject.Inject

class FissuresViewModel @Inject constructor(
        private val repo: FissuresRepository
) : BaseViewModel() {

    companion object {
        private const val TIER_LITH = 1
        private const val TIER_MESO = 2
        private const val TIER_NEO = 3
        private const val TIER_AXI = 4
    }

    val fissures = BehaviorSubject.create<List<FissuresModel>>()
    val showLoading = BehaviorSubject.create<Boolean>()
    val showFailure = BehaviorSubject.create<Boolean>()

    init {
        refresh()
    }

    /**
     * Requests new data to be loaded. Not to be called on ViewModel
     * initialization as it will request the data by itself.
     */
    fun refresh() {
        showFailure.onNext(false)
        showLoading.onNext(true)
        disposable.add(
                repo.getFissures()
                        .subscribeOn(Schedulers.io())
                        .map { list -> list.filter { !it.expired } }
                        .map(::mapFissures)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(::onSuccess, ::onFailure)
        )
    }

    private fun mapFissures(resource: List<Fissure>): List<FissuresModel> {
        return resource.map {
            FissuresModel(
                    it.id,
                    it.node,
                    it.missionType,
                    it.enemy,
                    expiryInMillis(it.expiry),
                    getResourceForTier(it.tierNum)
            )
        }
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

    private fun onSuccess(data: List<FissuresModel>) {
        showLoading.onNext(false)
        showFailure.onNext(false)
        fissures.onNext(data)
    }

    private fun onFailure(throwable: Throwable) {
        showLoading.onNext(false)
        showFailure.onNext(true)
    }

}