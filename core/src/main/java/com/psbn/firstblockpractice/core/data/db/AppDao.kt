package com.psbn.firstblockpractice.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.psbn.firstblockpractice.core.data.db.entity.CategoryDbModel
import com.psbn.firstblockpractice.core.data.db.entity.EventDbModel

@Dao
interface AppDao {

    @Query("SELECT * FROM events")
    fun getEvents(): List<EventDbModel>

    @Query("UPDATE events SET isRead = 1 WHERE id = :id")
    suspend fun markAsReadEvent(id: Int)

    @Query("SELECT * FROM categories ")
    fun getCategories(): List<CategoryDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryDbModel>)

}