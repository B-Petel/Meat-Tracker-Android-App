package com.bpetel.meattracker.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.data.model.MeatEntity
import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.presentation.history.model.FormState
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.IsoFields

class HistoryViewModel(
    private val meatRepository: MeatRepository
): ViewModel() {

    fun onSubmit(formState: FormState) {
        val timestamp = System.currentTimeMillis()
        val date = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()

        val meatEntity = MeatEntity(
            id = formState.id ?: 0,
            type = formState.type,
            meatPart = formState.meatParts,
            weightInGrams = formState.weightInGrams,
            date = timestamp,
            day = date.dayOfWeek.value,
            week = date.get(IsoFields.WEEK_BASED_YEAR),
            month = date.monthValue
        )

        viewModelScope.launch {
            formState.id?.let { meatRepository.update(meatEntity) } ?: meatRepository.insert(meatEntity)
        }
    }

    fun onDelete(meatEntity: MeatEntity) {
        viewModelScope.launch {
            meatRepository.delete(meatEntity)
        }
    }
}