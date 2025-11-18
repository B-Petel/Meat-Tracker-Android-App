package com.bpetel.meattracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Meat(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("meat_part")
    val mealPart: String,
    @ColumnInfo("weight")
    val weightInGrams: Int
)
