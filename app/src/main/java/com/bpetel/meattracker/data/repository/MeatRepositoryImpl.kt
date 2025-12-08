package com.bpetel.meattracker.data.repository

import android.util.Log
import com.bpetel.meattracker.data.AppDatabase
import com.bpetel.meattracker.data.model.MeatEntity
import com.bpetel.meattracker.domain.repository.MeatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

class MeatRepositoryImpl(
    private val db: AppDatabase
): MeatRepository {
    lateinit var listMeatEntityByDate: Flow<Map<LocalDate, List<MeatEntity>>>

    override fun getAllMeatGroupByDate(): Flow<Map<LocalDate, List<MeatEntity>>> {
        try {
            listMeatEntityByDate = db.meatDao().getAll().map {
                it.groupBy { it.localDate }
            }
        } catch (e: Exception) {
            Log.e("Database Error", "Error retrieving entry history: " + e.message)
        }
        return listMeatEntityByDate
    }

    override fun getTotalMeatWeightByDate(): Flow<Map<LocalDate, Int>> {
        return getAllMeatGroupByDate().map { map ->
            map.mapValues { (_, objects) ->
                objects.sumOf { it.weightInGrams }
            }
        }
    }

    override fun getTotalByDayOfWeek(): Flow<Map<Int, Int>> {
        val currentWeek = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
        return db.meatDao().getTotalByDay(currentWeek)
    }

    override fun getTotalByWeekOfYear(): Flow<Map<Int, Int>> {
        val month = LocalDate.now().month.value
        return db.meatDao().getTotalByWeek(month)
    }

    override fun getTotalByMonth(): Flow<Map<Int, Int>> {
        val year = LocalDate.now().year
        return db.meatDao().getTotalByMonth(year)
    }

    override fun getTotalByType(): Flow<Map<String, Float>> {
        return db.meatDao().getTotalByType()
    }

    override suspend fun insert(meatEntity: MeatEntity) {
        try {
            db.meatDao().insert(meatEntity)
        } catch (e: Exception) {
            Log.e("Database Error", "Error inserting meat entry: " + e.message)
        }
    }

    override suspend fun update(meatEntity: MeatEntity) {
        try {
            db.meatDao().update(meatEntity)
        } catch (e: Exception) {
            Log.e("Database Error", "Error updating meat entry: " + e.message);
        }
    }

    override suspend fun delete(meatEntity: MeatEntity) {
        try {
            db.meatDao().delete(meatEntity)
        } catch (e: Exception) {
            Log.e("Database Error", "Error deleting meat entry: " + e.message);
        }
    }
}