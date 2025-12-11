package com.bpetel.meattracker.data.model

import com.bpetel.meattracker.domain.model.Meat
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.WeekFields
import java.util.Locale

object Mapper {
    fun Meat.toEntity(): MeatEntity {
        val date = Instant.ofEpochMilli(this.timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
        return MeatEntity(
            id = this.id,
            type = this.type,
            part = this.part,
            weightInGr = this.weightInGr,
            timestamp = this.timestamp,
            day = date.dayOfMonth,
            week = date.get(WeekFields.of(Locale.getDefault()).weekOfYear()),
            month = date.monthValue
        )
    }

    fun MeatEntity.toDomain(): Meat {
        return Meat(
            id = this.id,
            type = this.type,
            part = this.part,
            weightInGr = this.weightInGr,
            timestamp = this.timestamp
        )
    }
}