package com.kawaiistudios.warframecompanion.data.db.dao

import androidx.room.*
import io.reactivex.Completable

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAbort(obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAbort(vararg obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAbort(obj: List<T>): Completable


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(vararg obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: List<T>): Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(vararg obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(obj: List<T>): Completable


    @Update
    fun update(obj: T)

    @Update
    fun update(vararg obj: T)

    @Update
    fun update(obj: List<T>)


    @Delete
    fun delete(obj: T)

    @Delete
    fun delete(vararg obj: T)

    @Delete
    fun delete(obj: List<T>)

}