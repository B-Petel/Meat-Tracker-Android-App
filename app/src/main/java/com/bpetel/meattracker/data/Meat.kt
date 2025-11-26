package com.bpetel.meattracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Serializable
@Entity
data class Meat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("meat_part")
    val meatPart: String,
    @ColumnInfo("weight")
    val weightInGrams: Int,
    @ColumnInfo("date")
    val date: Long
) {
    val localDate: LocalDate
        get() = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate()
}

