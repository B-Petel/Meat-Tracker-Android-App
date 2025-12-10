package com.bpetel.meattracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Upsert
import com.bpetel.meattracker.data.model.MeatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeatDao {
    @Query("SELECT * FROM meatEntity ORDER BY date DESC")
    fun getAll(): Flow<List<MeatEntity>>

    @Query(
        """
        SELECT day, SUM(weight) as total
        FROM MeatEntity
        WHERE week = :week
        GROUP BY day
    """
    )
    fun getTotalWeightByWeek(week: Int):
            Flow<Map<@MapColumn("day") String, @MapColumn("total") Float>>

    @Query(
        """
        SELECT day, SUM(weight) as total
        FROM MeatEntity
        WHERE month = :month
        GROUP BY day
    """
    )
    fun getTotalWeightByMonth(month: String):
            Flow<Map<@MapColumn("day") String, @MapColumn("total") Float>>

    @Query(
        """
        SELECT month, SUM(weight) as total
        FROM MeatEntity
        WHERE strftime('%Y', date / 1000, 'unixepoch') = :year
        GROUP BY month
    """
    )
    fun getTotalWeightByYear(year: String):
            Flow<Map<@MapColumn("month") String, @MapColumn("total") Float>>

    @Query(
        """
        SELECT type, SUM(weight) as total
        FROM MeatEntity
        GROUP BY type
    """
    )
    fun getTotalWeightByType():
            Flow<Map<@MapColumn("type") String, @MapColumn("total") Float>>

    @Upsert
    suspend fun upsert(meatEntity: MeatEntity)

    @Delete
    suspend fun delete(meatEntity: MeatEntity)
}