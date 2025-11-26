package com.bpetel.meattracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.domain.usecase.GetTotalMeatWeightGroupByDateUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

class HomeViewModel(
    getTotalMeatWeightUseCase: GetTotalMeatWeightGroupByDateUseCase,
): ViewModel() {

    val homeState = getTotalMeatWeightUseCase.invoke().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyMap()
    )
}