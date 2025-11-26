package com.bpetel.meattracker.presentation.form

data class FormState(
    val id: Int?,
    val type: String,
    val meatParts: String,
    val weightInGrams: Int
)