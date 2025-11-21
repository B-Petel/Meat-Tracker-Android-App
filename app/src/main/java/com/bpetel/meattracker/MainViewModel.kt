package com.bpetel.meattracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.domain.LocalRepository
import com.bpetel.meattracker.presentation.form.FormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel(
    private val repo: LocalRepository
): ViewModel() {
    val state = repo.getHistory().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyList()
    )

    fun onSubmit(formState: FormState) {
        val newMeat = Meat(
            id = formState.id ?: 0,
            type = formState.type,
            mealPart = formState.mealPart,
            weightInGrams = formState.weightInGrams.toIntOrNull() ?: 0,
            date = System.currentTimeMillis()
        )

        viewModelScope.launch {
            if (formState.id == null) {
                repo.insert(newMeat)
            } else {
                repo.update(newMeat)
            }
        }
    }

    fun onDelete(meat: Meat) {
        viewModelScope.launch {
            repo.delete(meat)
        }
    }
}