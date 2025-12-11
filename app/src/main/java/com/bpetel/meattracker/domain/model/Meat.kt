package com.bpetel.meattracker.domain.model

import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Serializable
data class Meat(
    val id: Int = 0,
    val type: String = "",
    val part: String = "",
    val weightInGr: Int = 0,
    val timestamp: Long = 0
) {
    val localDate: LocalDate
        get() = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
}
