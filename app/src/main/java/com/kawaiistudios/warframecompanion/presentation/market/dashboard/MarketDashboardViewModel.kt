package com.kawaiistudios.warframecompanion.presentation.market.dashboard

import com.kawaiistudios.warframecompanion.data.repository.MarketItemsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MarketDashboardViewModel @Inject constructor(
        val repository: MarketItemsRepository
) : BaseViewModel() {

    val queryResults = BehaviorSubject.createDefault(emptyList<MarketDashboardQueryModel>())
    val showClear = BehaviorSubject.createDefault(false)

    init {
        disposable.add(
                repository.fetchItems()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ }, { })
        )
    }

    fun query(query: String) {
        showClear.onNext(query.isNotEmpty())
        if (query.isEmpty()) {
            queryResults.onNext(emptyList())
            return
        }

        disposable.add(
                repository.findItems(query, 10)
                        .map { list -> list.map { MarketDashboardQueryModel(it.id, it.itemName) }}
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ queryResults.onNext(it) }, {})
        )
    }

}