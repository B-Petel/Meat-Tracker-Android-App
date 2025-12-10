package com.bpetel.meattracker.domain.repository

import com.bpetel.meattracker.domain.model.Meat
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MeatRepository {
    fun getAllMeatGroupByDate(): Flow<Map<LocalDate, List<Meat>>>
    fun getTotalWeightByWeek(): Flow<Map<String, Float>>
    fun getTotalWeightByMonth(): Flow<Map<String, Float>>
    fun getTotalWeightByYear(): Flow<Map<String, Float>>
    fun getTotalWeightByType(): Flow<Map<String, Float>>
    suspend fun upsert(meat: Meat)
    suspend fun delete(meat: Meat)
}