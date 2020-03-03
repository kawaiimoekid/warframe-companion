package com.kawaiistudios.warframecompanion.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.kawaiistudios.warframecompanion.data.db.entity.WarframeMarketItem
import io.reactivex.Single

@Dao
interface WarframeMarketItemDao : BaseDao<WarframeMarketItem> {

    @Query("select * from warframeMarketItems where id = :id")
    fun selectById(id: String): Single<WarframeMarketItem>

    @Query("select * from warframeMarketItems where itemName like :name order by itemName limit :limit")
    fun selectByName(name: String, limit: Int): Single<List<WarframeMarketItem>>

}