package com.bpetel.meattracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bpetel.meattracker.domain.model.Meat
import kotlinx.serialization.Serializable
import java.time.DayOfWeek
import java.time.Instant
import java.time.Month
import java.time.ZoneId
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

@Serializable
@Entity(
    indices = [
        Index(value = ["week"]),
        Index(value = ["month"]),
        Index(value = ["type"])
    ]
)
data class MeatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("part") val part: String,
    @ColumnInfo("weight") val weightInGr: Int,
    @ColumnInfo("date") val timestamp: Long,
    @ColumnInfo("day") val dayString: String,
    @ColumnInfo("week") val weekString: String,
    @ColumnInfo("month") val monthString: String
)

fun Meat.toEntity(): MeatEntity {
    val date = Instant.ofEpochMilli(this.timestamp).atZone(ZoneId.systemDefault()).toLocalDate()

    return MeatEntity(
        id = this.id,
        type = this.type,
        part = this.part,
        weightInGr = this.weightInGr,
        timestamp = this.timestamp,
        dayString = DayOfWeek
            .of(date.dayOfWeek.value)
            .getDisplayName(TextStyle.SHORT, Locale.FRENCH)
            .toString(),
        weekString = date.get(WeekFields
            .of(Locale.getDefault()).weekOfYear())
            .toString(),
        monthString = Month
            .of(date.month.value)
            .getDisplayName(TextStyle.SHORT, Locale.FRENCH)
            .toString()
    )
}

fun MeatEntity.toDomain(): Meat {
    return Meat(
        id = this.id,
        type = this.type,
        part = this.part,
        weightInGr = this.weightInGr,
        timestamp = this.timestamp,
    )
}

