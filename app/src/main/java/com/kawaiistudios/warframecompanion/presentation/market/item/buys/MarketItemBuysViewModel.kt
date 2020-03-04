package com.kawaiistudios.warframecompanion.presentation.market.item.buys

import android.util.Log
import com.kawaiistudios.warframecompanion.data.repository.MarketItemsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MarketItemBuysViewModel @Inject constructor(
        private val repository: MarketItemsRepository
) : BaseViewModel() {

    val orders = BehaviorSubject.createDefault(emptyList<MarketItemBuysModel>())
    val showNoOrders = BehaviorSubject.createDefault(false)

    fun init(itemId: String) {
        disposable.add(
                repository.getItemById(itemId)
                        .flatMap { repository.getItemBuys(it.urlName) }
                        .map { list -> list.map { MarketItemBuysModel(it.id, it.user.name, it.platinum.toInt(), it.quantity, it.user.status) }}
                        .map { list -> list.sortedWith(compareBy({ it.userStatus }, { it.platinum })) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            orders.onNext(it)
                            showNoOrders.onNext(it.isEmpty())
                        }, { error ->
                            Log.e("Error", "ERROR >:(", error)
                        })
        )
    }

}