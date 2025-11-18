package com.bpetel.meattracker.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface MeatDao {
    @Query("SELECT * FROM meat")
    fun getAll(): List<Meat>

    @Insert
    fun insert(meat: Meat)

    @Update
    fun update(meat: Meat)

    @Delete
    fun delete(meat: Meat)
}