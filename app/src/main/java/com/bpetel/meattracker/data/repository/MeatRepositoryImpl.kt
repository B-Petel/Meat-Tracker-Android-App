package com.bpetel.meattracker.data.repository

import android.util.Log
import com.bpetel.meattracker.data.AppDatabase
import com.bpetel.meattracker.data.model.toDomain
import com.bpetel.meattracker.data.model.toEntity
import com.bpetel.meattracker.domain.model.Meat
import com.bpetel.meattracker.domain.repository.MeatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

class MeatRepositoryImpl(
    private val db: AppDatabase
): MeatRepository {
    lateinit var listMeatEntityByDate: Flow<Map<LocalDate, List<Meat>>>

    override fun getAllMeatGroupByDate(): Flow<Map<LocalDate, List<Meat>>> {
        try {
            listMeatEntityByDate = db.meatDao().getAll().map {
                it.onEach { data -> println("Repository : All data: $data") }
                it.map { entity ->
                    entity.toDomain()
                }.groupBy { meat ->
                    meat.localDate
                }
            }
        } catch (e: Exception) {
            Log.e("Database Error", "Error retrieving entry history: " + e.message)
        }
        return listMeatEntityByDate
    }

    override fun getTotalWeightByWeek(): Flow<Map<String, Float>> {
        val currentWeek = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear())
        return db.meatDao().getTotalWeightByWeek(currentWeek)
            .onEach { data -> println("Repository : Week data: $data") }
    }

    override fun getTotalWeightByMonth(): Flow<Map<String, Float>> {
        val month = Month
            .of(LocalDate.now().monthValue)
            .getDisplayName(TextStyle.SHORT, Locale.FRENCH)
            .toString()
        return db.meatDao().getTotalWeightByMonth(month)
            .onEach { data -> println("Repository : Month data: $data") }
    }

    override fun getTotalWeightByYear(): Flow<Map<String, Float>> {
        val year = LocalDate.now().year.toString()
        return db.meatDao().getTotalWeightByYear(year)
            .onEach { data -> println("Repository : Year data: $data") }
    }

    override fun getTotalWeightByType(): Flow<Map<String, Float>> {
        return db.meatDao().getTotalWeightByType()
            .onEach { data -> println("Repository : Type data: $data") }
    }

    override suspend fun upsert(meat: Meat) {
        try {
            db.meatDao().upsert(meat.toEntity())
        } catch (e: Exception) {
            Log.e("Database Error", "Error inserting or updating meat entry: " + e.message);
        }
    }


    override suspend fun delete(meat: Meat) {
        try {
            db.meatDao().delete(meat.toEntity())
        } catch (e: Exception) {
            Log.e("Database Error", "Error deleting meat entry: " + e.message);
        }
    }
}