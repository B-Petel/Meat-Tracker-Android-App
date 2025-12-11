package com.bpetel.meattracker.presentation.utils

import java.time.LocalDate

object LocalDateToRelativeDateString {
    fun LocalDate.toRelativeDateString(): String {
        val today = LocalDate.now()
        val daysBetween = today.dayOfMonth - this.dayOfMonth
        val monthBetween = today.month.value - this.month.value
        val yearBetween = today.year - this.year

        return when {
            yearBetween > 0 -> "Il y a $yearBetween an${if (yearBetween > 1) "s" else ""}"
            monthBetween in 1..11 -> "Il y a $monthBetween mois"
            daysBetween in 14..29 -> "Il y a ${daysBetween / 7} semaines"
            daysBetween in 7..13 -> "Il y a ${daysBetween / 7} semaine${if (daysBetween / 7 > 1) "s" else ""}"
            daysBetween in 2..6 -> "Il y a $daysBetween jours"
            daysBetween == 1 -> "Hier"

            else -> {
                "Aujourd'hui"
            }
        }
    }
}