package com.bpetel.meattracker.data

import android.util.Log
import com.bpetel.meattracker.domain.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class LocalRepositoryImpl(
    private val db: AppDatabase
): LocalRepository {
    lateinit var listMeatByDate: Flow<Map<LocalDate, List<Meat>>>

    override fun getAllMeatGroupByDate(): Flow<Map<LocalDate, List<Meat>>> {
        try {
            listMeatByDate = db.meatDao().getAll().map {
                it.groupBy { it.localDate }
            }
        } catch (e: Exception) {
            Log.e("Database Error", "Error retrieving entry history: " + e.message)
        }
        return listMeatByDate
    }

    override fun getTotalMeatWeightByDate(): Flow<Map<LocalDate, Int>> {
        return getAllMeatGroupByDate().map { map ->
            map.mapValues { (_, objects) ->
                objects.sumOf { it.weightInGrams }
            }
        }
    }

    override suspend fun insert(meat: Meat) {
        try {
            db.meatDao().insert(meat)
        } catch (e: Exception) {
            Log.e("Database Error", "Error inserting meat entry: " + e.message)
        }
    }

    override suspend fun update(meat: Meat) {
        try {
            db.meatDao().update(meat)
        } catch (e: Exception) {
            Log.e("Database Error", "Error updating meat entry: " + e.message);
        }
    }

    override suspend fun delete(meat: Meat) {
        try {
            db.meatDao().delete(meat)
        } catch (e: Exception) {
            Log.e("Database Error", "Error deleting meat entry: " + e.message);
        }
    }
}