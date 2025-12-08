package com.bpetel.meattracker.domain.repository

import com.bpetel.meattracker.data.model.MeatEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MeatRepository {
    fun getAllMeatGroupByDate(): Flow<Map<LocalDate, List<MeatEntity>>>
    fun getTotalMeatWeightByDate(): Flow<Map<LocalDate, Int>>
    fun getTotalByDayOfWeek(): Flow<Map<Int, Int>>
    fun getTotalByWeekOfYear(): Flow<Map<Int, Int>>
    fun getTotalByMonth(): Flow<Map<Int, Int>>
    fun getTotalByType(): Flow<Map<String, Float>>
    suspend fun insert(meatEntity: MeatEntity)
    suspend fun update(meatEntity: MeatEntity)
    suspend fun delete(meatEntity: MeatEntity)
}