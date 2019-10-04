package com.kawaiistudios.warframecompanion.presentation.sortie

import android.os.CountDownTimer
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.data.model.Sortie
import com.kawaiistudios.warframecompanion.data.repository.SortiesRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder
import javax.inject.Inject

class SortieViewModel @Inject constructor(
        private val repo: SortiesRepository
) : BaseViewModel() {

    companion object {
        private const val FACTION_GRINEER = "Grineer"
        private const val FACTION_CORPUS = "Corpus"
        private const val FACTION_INFESTED = "Infestation"
    }

    private var timer: CountDownTimer? = null
    private val periodFormatter = PeriodFormatterBuilder()
            .minimumPrintedDigits(2)
            .appendHours()
            .appendSuffix("h ")
            .appendMinutes()
            .appendSuffix("m ")
            .appendSeconds()
            .appendSuffix("s")
            .toFormatter()

    val sortie = BehaviorSubject.create<SortieModel>()
    val timeLeft = BehaviorSubject.createDefault("")
    val showSortie = BehaviorSubject.createDefault(false)
    val showFailure = BehaviorSubject.createDefault(false)
    val showLoading = BehaviorSubject.createDefault(true)

    init {
        refresh()
    }

    fun refresh() {
        showSortie.onNext(false)
        showFailure.onNext(false)
        showLoading.onNext(true)

        disposable.add(
                repo.getSortie()
                        .subscribeOn(Schedulers.io())
                        .map(::mapSortie)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showSortie.onNext(true)
                            showFailure.onNext(false)
                            showLoading.onNext(false)
                            sortie.onNext(it)

                            if (it.millisUntilExpiry > 0) {
                                startTimer(it.millisUntilExpiry)
                            }
                        }, {
                            showSortie.onNext(false)
                            showFailure.onNext(true)
                            showLoading.onNext(false)
                        })
        )
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

    private fun startTimer(millis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millis, 1000) {
            override fun onFinish() { }
            override fun onTick(millis: Long) {
                timeLeft.onNext(periodFormatter.print(Duration.millis(millis).toPeriod()))
            }
        }

        timer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
        timer = null
    }

}