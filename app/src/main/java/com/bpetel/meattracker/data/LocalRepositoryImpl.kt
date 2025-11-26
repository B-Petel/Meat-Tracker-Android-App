package com.bpetel.meattracker.data

import android.util.Log
import com.bpetel.meattracker.domain.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class LocalRepositoryImpl(
    private val db: AppDatabase
): LocalRepository {

    lateinit var meatsFlow: Flow<Map<LocalDate, List<Meat>>>

    override fun getHistory(): Flow<Map<LocalDate, List<Meat>>> {
        try {
            meatsFlow = db.meatDao().getAll().map {
                it.groupBy { it.localDate }
            }
        } catch (e: Exception) {
            Log.e("Database Error", "Error retrieving entry history: " + e.message)
        }
        return meatsFlow
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
            Log.e("Database Error", "Error inserting meat entry: " + e.message);
        }
    }

    override suspend fun delete(meat: Meat) {
        try {
            db.meatDao().delete(meat)
        } catch (e: Exception) {
            Log.e("Database Error", "Error inserting meat entry: " + e.message);
        }
    }
}