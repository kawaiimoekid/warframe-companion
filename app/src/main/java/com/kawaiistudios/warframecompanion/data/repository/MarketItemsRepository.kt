package com.kawaiistudios.warframecompanion.data.repository

import com.kawaiistudios.warframecompanion.data.db.dao.WarframeMarketItemDao
import com.kawaiistudios.warframecompanion.data.web.market.WarframeMarketApi
import javax.inject.Inject

class MarketItemsRepository @Inject constructor(
        private val api: WarframeMarketApi,
        private val dao: WarframeMarketItemDao
) {

    private companion object {
        private const val ORDER_TYPE_SALE = "sell"
        private const val ORDER_TYPE_BUY = "buy"
    }

    fun fetchItems() =
            api.getItems()
                    .map { it.payload.items.map { res -> res.toEntity() } }
                    .flatMapCompletable { dao.insertIgnore(it) }

    fun getItemById(itemId: String) = dao.selectById(itemId)
    fun findItems(itemName: String, count: Int = 5) = dao.selectByName("%$itemName%", count)

    fun getItemSales(itemUrlName: String) =
            api.getItemOrders(itemUrlName)
                    .map { it.payload.orders.filter { item ->
                        item.orderType == ORDER_TYPE_SALE && item.isVisible
                    }}

    fun getItemBuys(itemUrlName: String) =
            api.getItemOrders(itemUrlName)
                    .map { it.payload.orders.filter { item ->
                        item.orderType == ORDER_TYPE_BUY && item.isVisible
                    }}

    fun addItemToObserved(itemId: String) =
            dao.selectById(itemId)
                    .map { it.copy(isObserved = true) }
                    .flatMapCompletable { dao.insertReplace(it) }

    fun removeItemFromObserved(itemId: String) =
            dao.selectById(itemId)
                    .map { it.copy(isObserved = false) }
                    .flatMapCompletable { dao.insertReplace(it) }

}