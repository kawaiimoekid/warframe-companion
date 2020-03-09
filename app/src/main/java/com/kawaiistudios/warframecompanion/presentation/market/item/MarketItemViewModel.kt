package com.kawaiistudios.warframecompanion.presentation.market.item

import com.kawaiistudios.warframecompanion.data.repository.MarketItemsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MarketItemViewModel @Inject constructor(
        val repo: MarketItemsRepository
) : BaseViewModel() {

    private lateinit var itemId: String

    val title = BehaviorSubject.create<String>()
    val isFavorite = BehaviorSubject.create<Boolean>()

    fun init(itemId: String) {
        this.itemId = itemId
        disposable.add(
                repo.getItemById(itemId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            title.onNext(it.itemName)
                            isFavorite.onNext(it.isObserved)
                        }, {})
        )
    }

    fun setFavorite(newState: Boolean) {
        val operation = if (newState) repo.addItemToObserved(itemId) else repo.removeItemFromObserved(itemId)
        disposable.add(
                operation.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ isFavorite.onNext(newState) }, {})
        )
    }

}