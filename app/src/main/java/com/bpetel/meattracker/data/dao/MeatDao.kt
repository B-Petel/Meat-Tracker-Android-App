package com.bpetel.meattracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Update
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
    fun getTotalByDay(week: Int):
            Flow<Map<@MapColumn("day") Int, @MapColumn("total") Int>>

    @Query(
        """
        SELECT day, SUM(weight) as total
        FROM MeatEntity
        WHERE month = :month
        GROUP BY week
    """
    )
    fun getTotalByWeek(month: Int):
            Flow<Map<@MapColumn("day") Int, @MapColumn("total") Int>>

    @Query(
        """
        SELECT month, SUM(weight) as total
        FROM MeatEntity
        WHERE strftime('%Y', date / 1000, 'unixepoch') = :year
        GROUP BY month
    """
    )
    fun getTotalByMonth(year: Int):
            Flow<Map<@MapColumn("month") Int, @MapColumn("total") Int>>

    @Query(
        """
        SELECT type, SUM(weight) as total
        FROM MeatEntity
        GROUP BY type
    """
    )
    fun getTotalByType():
            Flow<Map<@MapColumn("type") String, @MapColumn("total") Float>>

    @Insert
    suspend fun insert(meatEntity: MeatEntity)

    @Update
    suspend fun update(meatEntity: MeatEntity)

    @Delete
    suspend fun delete(meatEntity: MeatEntity)
}