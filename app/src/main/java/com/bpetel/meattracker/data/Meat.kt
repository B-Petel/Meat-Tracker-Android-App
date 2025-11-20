package com.bpetel.meattracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Meat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("meat_part")
    val mealPart: String,
    @ColumnInfo("weight")
    val weightInGrams: Int,
    @ColumnInfo("date")
    val date: Long
)
