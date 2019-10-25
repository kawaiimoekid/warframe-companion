package com.kawaiistudios.warframecompanion.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.kawaiistudios.warframecompanion.data.db.entity.WarframeMarketItem
import io.reactivex.Single

@Dao
interface WarframeMarketItemDao : BaseDao<WarframeMarketItem> {

    @Query("select * from warframeMarketItems where itemName like :name")
    fun selectByName(name: String): Single<List<WarframeMarketItem>>

}