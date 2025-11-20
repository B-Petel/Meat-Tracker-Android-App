package com.bpetel.meattracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.domain.LocalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
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
        viewModelScope.launch {
            val newMeat = Meat(
                type = formState.type,
                mealPart = formState.mealPart,
                weightInGrams = formState.weightInGrams.toIntOrNull() ?: 0,
                date = System.currentTimeMillis()
            )
            repo.insert(newMeat)
        }
    }
}