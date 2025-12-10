package com.bpetel.meattracker.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {
    @Serializable
    data object Home: Screen()
    @Serializable
    data object History: Screen()
    @Serializable
    data class AddMeatEntry(
        val id: Int? = null,
        val type: String = "",
        val parts: String = "",
        val weight: Float = 0f,
        val timestamp: Long = 0
    ): Screen()
}