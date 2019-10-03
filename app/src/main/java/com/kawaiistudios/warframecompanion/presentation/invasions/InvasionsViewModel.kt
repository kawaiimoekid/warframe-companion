package com.kawaiistudios.warframecompanion.presentation.invasions

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.data.model.invasion.Invasion
import com.kawaiistudios.warframecompanion.data.repository.InvasionsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class InvasionsViewModel @Inject constructor(
        private val repo: InvasionsRepository
) : BaseViewModel() {

    private val icons = mapOf(
            "Grineer" to R.drawable.ic_grineer,
            "Corpus" to R.drawable.ic_corpus,
            "Infested" to R.drawable.ic_infested
    )
    private val colors = mapOf(
            "Grineer" to R.color.grineer,
            "Corpus" to R.color.corpus,
            "Infested" to R.color.infested
    )

    val invasions = BehaviorSubject.create<List<InvasionsModel>>()
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
                repo.getInvasions()
                        .subscribeOn(Schedulers.io())
                        .map(::mapInvasions)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(::onSuccess, ::onFailure)
        )
    }

    private fun mapInvasions(resource: List<Invasion>): List<InvasionsModel> {
        return resource.map {
            InvasionsModel(
                    id = it.id,
                    node = it.node,
                    attackerIcon = icons[it.attackingFaction] ?: R.drawable.ic_error_black_24dp,
                    attackerIconColor = colors[it.attackingFaction] ?: R.color.textPrimary,
                    attackerName = it.attackingFaction,
                    attackerReward = it.attackerReward.countedItems.getOrNull(0)?.type ?: "",
                    defenderIcon = icons[it.defendingFaction] ?: R.drawable.ic_error_black_24dp,
                    defenderIconColor = colors[it.defendingFaction] ?: R.color.textPrimary,
                    defenderName = it.defendingFaction,
                    defenderReward = it.defenderReward.countedItems.getOrNull(0)?.type ?: ""
            )
        }
    }

    private fun onSuccess(data: List<InvasionsModel>) {
        showLoading.onNext(false)
        showFailure.onNext(false)
        invasions.onNext(data)
    }

    private fun onFailure(throwable: Throwable) {
        showLoading.onNext(false)
        showFailure.onNext(true)
    }

}