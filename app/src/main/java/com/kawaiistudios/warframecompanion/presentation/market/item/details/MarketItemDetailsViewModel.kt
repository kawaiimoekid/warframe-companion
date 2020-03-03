package com.kawaiistudios.warframecompanion.presentation.market.item.details

import com.kawaiistudios.warframecompanion.data.repository.MarketItemsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MarketItemDetailsViewModel @Inject constructor(
        val repository: MarketItemsRepository
) : BaseViewModel() {



    fun init(itemId: String) {
        disposable.add(
                repository.getItemById(itemId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({}, {})
        )
    }

}