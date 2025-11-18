package com.bpetel.meattracker.data

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface UserDao {
    @Query("SELECT * FROM user")
    fun get(): User

    @Insert
    fun insert(): User

    @Update
    fun update(user: User)
}