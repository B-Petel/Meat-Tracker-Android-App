package com.bpetel.meattracker.presentation.form

data class FormState(
    val id: Int?,
    val type: String,
    val mealPart: String,
    val weightInGrams: String
)