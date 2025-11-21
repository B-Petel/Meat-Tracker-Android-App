package com.bpetel.meattracker.domain

import com.bpetel.meattracker.data.Meat
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getHistory(): Flow<List<Meat>>

    suspend fun insert(meat: Meat)

    suspend fun update(meat: Meat)

    suspend fun delete(meat: Meat)
}