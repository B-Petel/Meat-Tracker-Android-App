package com.bpetel.meattracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class MeatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("part") val part: String,
    @ColumnInfo("weight") val weightInGr: Int,
    @ColumnInfo("date") val timestamp: Long,
    @ColumnInfo("day") val day: Int,
    @ColumnInfo("week") val week: Int,
    @ColumnInfo("month") val month: Int
)
