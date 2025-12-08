package com.bpetel.meattracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Serializable
@Entity(
    indices = [
        Index(value = ["week"]),      // Index pour les requêtes par semaine
        Index(value = ["month"]),     // Index pour les requêtes par mois
        Index(value = ["day", "week"]), // Index composite pour les requêtes par jour dans une semaine
        Index(value = ["type"])
    ]
)
data class MeatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("meat_part")
    val meatPart: String,
    @ColumnInfo("weight")
    val weightInGrams: Int,
    @ColumnInfo("date")
    val date: Long,

    val day: Int,

    val week: Int,

    val month: Int
) {
    val localDate: LocalDate
        get() = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate()
}

