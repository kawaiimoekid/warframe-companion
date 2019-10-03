package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.invasions

import com.kawaiistudios.warframecompanion.data.model.invasion.Invasion
import com.kawaiistudios.warframecompanion.data.repository.InvasionsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class DashboardInvasionsWidgetViewModel @Inject constructor(
        repo: InvasionsRepository
) : BaseViewModel() {

    val rewards = BehaviorSubject.create<List<DashboardInvasionsWidgetModel>>()

    init {
        rewards.onNext(emptyList())
        disposable.add(
                repo.getInvasions()
                        .subscribeOn(Schedulers.io())
                        .map(::mapInvasions)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(rewards::onNext, rewards::onError)
        )
    }

    private fun mapInvasions(invasions: List<Invasion>): List<DashboardInvasionsWidgetModel> {
        return invasions.asSequence()
                .filter { !it.completed }
                .map { listOf(it.attackerReward, it.defenderReward) }
                .flatten()
                .filter { it.countedItems.isNotEmpty() }
                .groupBy { it.countedItems[0].type }
                .map { rewardGroup ->
                    DashboardInvasionsWidgetModel(
                            UUID.randomUUID(),
                            "${rewardGroup.key} (x${rewardGroup.value.sumBy { it.countedItems[0].count }})",
                            rewardGroup.value[0].thumbnail
                    )
                }
                .sortedBy { it.text }
                .toList()
    }

}