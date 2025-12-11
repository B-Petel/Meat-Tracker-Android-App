package com.bpetel.meattracker.data.repository

import android.util.Log
import com.bpetel.meattracker.data.AppDatabase
import com.bpetel.meattracker.data.model.Mapper.toDomain
import com.bpetel.meattracker.data.model.Mapper.toEntity
import com.bpetel.meattracker.domain.model.Meat
import com.bpetel.meattracker.domain.repository.MeatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MeatRepositoryImpl(
    private val db: AppDatabase
): MeatRepository {
    override fun getAllMeat(): Flow<List<Meat>> {
        return db.meatDao().getAll().map { it.map { it.toDomain() } }
    }

    override fun getTotalWeightByDayWeek(week: Int): Flow<Map<Long, Float>> {
        return db.meatDao().getTotalWeightByDayWeek(week)
            .onEach { data -> println("Repository : Week data: $data") }
    }

    override fun getTotalWeightByDayMonth(week: Int, month: Int): Flow<Map<Int, Float>> {
        return db.meatDao().getTotalWeightByDayMonth(month)
            .onEach { data -> println("Repository : Month data: $data") }
    }

    override fun getTotalWeightByMonth(year: String): Flow<Map<Int, Float>> {
        return db.meatDao().getTotalWeightByMonth(year)
            .onEach { data -> println("Repository : Year data: $data") }
    }

    override fun getTotalWeightByType(): Flow<Map<String, Float>> {
        return db.meatDao().getTotalWeightByType()
            .onEach { data -> println("Repository : Type data: $data") }
    }

    override suspend fun upsert(meat: Meat) {
        val entity = meat.toEntity()
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