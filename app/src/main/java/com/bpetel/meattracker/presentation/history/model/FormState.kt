package com.bpetel.meattracker.presentation.history.model

data class FormState(
    val id: Int?,
    val type: String,
    val meatParts: String,
    val weightInGrams: Int,
    val date: Long
)