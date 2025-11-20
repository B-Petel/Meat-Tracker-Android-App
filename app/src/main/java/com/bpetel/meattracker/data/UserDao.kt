package com.bpetel.meattracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun get(): Flow<User>

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)
}