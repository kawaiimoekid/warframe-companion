package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.fissures

import com.kawaiistudios.warframecompanion.data.model.Fissure
import com.kawaiistudios.warframecompanion.data.repository.FissuresRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DashboardFissuresWidgetViewModel @Inject constructor(
        fissureRepo: FissuresRepository
) : BaseViewModel() {

    val fissures = BehaviorSubject.create<DashboardFissuresWidgetModel>()

    init {
        disposable.add(
                fissureRepo.getFissures()
                        .subscribeOn(Schedulers.io())
                        .map { fissures -> mapFissures(fissures) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(fissures::onNext, fissures::onError)
        )
    }

    private fun mapFissures(fissures: List<Fissure>) = DashboardFissuresWidgetModel(
            fissures.count { it.tierNum == 1 },
            fissures.count { it.tierNum == 2 },
            fissures.count { it.tierNum == 3 },
            fissures.count { it.tierNum == 4 }
    )

}