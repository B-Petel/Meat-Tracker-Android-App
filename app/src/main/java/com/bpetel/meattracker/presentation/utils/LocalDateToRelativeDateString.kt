package com.bpetel.meattracker.presentation.utils

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object LocalDateToRelativeDateString {
    fun LocalDate.toRelativeDateString(): String {
        val today = LocalDate.now()
        val daysBetween = ChronoUnit.DAYS.between(this, today).toInt()

        return when {
            daysBetween == 0 -> "Aujourd'hui"
            daysBetween == 1 -> "Hier"
            daysBetween in 2..6 -> "Il y a $daysBetween jours"
            daysBetween in 7..13 -> "Il y a ${daysBetween / 7} semaine${if (daysBetween / 7 > 1) "s" else ""}"
            daysBetween in 14..29 -> "Il y a ${daysBetween / 7} semaines"
            daysBetween in 30..59 -> "Il y a ${daysBetween / 30} mois"
            daysBetween in 60..364 -> {
                val months = daysBetween / 30
                "Il y a $months mois"
            }
            else -> {
                val years = daysBetween / 365
                "Il y a $years an${if (years > 1) "s" else ""}"
            }
        }
    }
}