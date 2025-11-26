package com.bpetel.meattracker.domain

import com.bpetel.meattracker.data.Meat
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface LocalRepository {
    fun getAllMeatGroupByDate(): Flow<Map<LocalDate, List<Meat>>>
    fun getTotalMeatWeightByDate(): Flow<Map<LocalDate, Int>>
    suspend fun insert(meat: Meat)
    suspend fun update(meat: Meat)
    suspend fun delete(meat: Meat)
}