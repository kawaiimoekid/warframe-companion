package com.kawaiistudios.warframecompanion.presentation.market.item

import com.kawaiistudios.warframecompanion.data.repository.MarketItemsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MarketItemViewModel @Inject constructor(
        val repository: MarketItemsRepository
) : BaseViewModel() {

    val title = BehaviorSubject.create<String>()
    val isFavorite = BehaviorSubject.create<Boolean>()

    fun init(itemId: String) {
        disposable.add(
                repository.getItemById(itemId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            title.onNext(it.itemName)
                            isFavorite.onNext(false)
                        }, {})
        )
    }

    fun setFavorite(newState: Boolean) {
        isFavorite.onNext(newState)
    }

}