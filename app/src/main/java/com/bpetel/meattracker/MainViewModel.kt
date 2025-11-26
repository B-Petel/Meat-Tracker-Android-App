package com.bpetel.meattracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.domain.usecase.DeleteMeatEntryUseCase
import com.bpetel.meattracker.domain.usecase.GetAllMeatGroupByDateUseCase
import com.bpetel.meattracker.domain.usecase.SaveMeatEntryUseCase
import com.bpetel.meattracker.presentation.form.FormState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel(
    getMeatUseCase: GetAllMeatGroupByDateUseCase,

    private val saveMeatUseCase: SaveMeatEntryUseCase,
    private val deleteMeatUseCase: DeleteMeatEntryUseCase
): ViewModel() {
    val state = getMeatUseCase.invoke().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyMap()
    )

    fun onSubmit(formState: FormState) {
        val meat = Meat(
            id = formState.id ?: 0,
            type = formState.type,
            meatPart = formState.meatParts,
            weightInGrams = formState.weightInGrams,
            date = System.currentTimeMillis()
        )

        viewModelScope.launch {
            saveMeatUseCase.invoke(id = formState.id, meat = meat)
        }
    }

    fun onDelete(meat: Meat) {
        viewModelScope.launch {
            deleteMeatUseCase(meat)
        }
    }
}