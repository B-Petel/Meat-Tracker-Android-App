package com.bpetel.meattracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class User(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "family_number")
    val familyNumber: Int
)
