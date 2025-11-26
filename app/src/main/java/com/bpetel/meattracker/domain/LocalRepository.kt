package com.bpetel.meattracker.domain

import com.bpetel.meattracker.data.Meat
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface LocalRepository {
    fun getHistory(): Flow<Map<LocalDate, List<Meat>>>

    suspend fun insert(meat: Meat)

    suspend fun update(meat: Meat)

    suspend fun delete(meat: Meat)
}