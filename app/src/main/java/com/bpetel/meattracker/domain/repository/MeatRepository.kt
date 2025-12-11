package com.bpetel.meattracker.domain.repository

import com.bpetel.meattracker.domain.model.Meat
import kotlinx.coroutines.flow.Flow

interface MeatRepository {
    fun getAllMeat(): Flow<List<Meat>>
    fun getTotalWeightByDayWeek(week: Int): Flow<Map<Long, Float>>
    fun getTotalWeightByDayMonth(week: Int, month: Int): Flow<Map<Int, Float>>
    fun getTotalWeightByMonth(year: String): Flow<Map<Int, Float>>
    fun getTotalWeightByType(): Flow<Map<String, Float>>
    suspend fun upsert(meat: Meat)
    suspend fun delete(meat: Meat)
}