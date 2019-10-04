package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.sortie

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

class DashboardSortieWidgetViewModel @Inject constructor(
        sortieRepo: SortiesRepository
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

    val sortie = BehaviorSubject.create<DashboardSortieWidgetModel>()
    val timeLeft = BehaviorSubject.createDefault("")

    init {
        disposable.add(
                sortieRepo.getSortie()
                        .subscribeOn(Schedulers.io())
                        .map(::mapSortie)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            sortie.onNext(it)
                            if (it.millisUntilExpiry > 0) {
                                startTimer(it.millisUntilExpiry)
                            }
                        }, sortie::onError)
        )
    }

    private fun mapSortie(sortie: Sortie) = DashboardSortieWidgetModel(
            expiryInMillis(sortie.expiry),
            sortie.faction,
            getResourceForFaction(sortie.faction),
            sortie.variants.getOrNull(0)?.missionType ?: "",
            sortie.variants.getOrNull(0)?.modifier ?: "",
            sortie.variants.getOrNull(1)?.missionType ?: "",
            sortie.variants.getOrNull(1)?.modifier ?: "",
            sortie.variants.getOrNull(2)?.missionType ?: "",
            sortie.variants.getOrNull(2)?.modifier ?: ""
    )

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