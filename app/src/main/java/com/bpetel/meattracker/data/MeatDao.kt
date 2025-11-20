package com.bpetel.meattracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MeatDao {
    @Query("SELECT * FROM meat")
    fun getAll(): Flow<List<Meat>>

    @Insert
    suspend fun insert(meat: Meat)

    @Update
    suspend fun update(meat: Meat)

    @Delete
    suspend fun delete(meat: Meat)
}