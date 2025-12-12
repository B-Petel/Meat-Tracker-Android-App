package com.bpetel.meattracker.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.domain.model.Meat
import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.presentation.history.model.FormState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HistoryViewModel(
    private val meatRepository: MeatRepository
): ViewModel() {

    val state = meatRepository.getAllMeat().map {
        it.groupBy { meat -> meat.localDate }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyMap()
    )
    fun onSubmit(formState: FormState) {
        val meat = Meat(
            id = formState.id ?: 0,
            type = formState.type,
            part = formState.meatParts,
            weightInGr = formState.weightInGrams,
            timestamp = 1762269326000
        )

        viewModelScope.launch {
            meatRepository.upsert(meat)
        }
    }

    fun onDelete(meat: Meat) {
        viewModelScope.launch {
            meatRepository.delete(meat)
        }
    }
}