package com.bpetel.meattracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.domain.usecase.GetTotalByPeriodUseCase
import com.bpetel.meattracker.presentation.utils.TimePeriod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


class HomeViewModel(
    meatRepository: MeatRepository,
    private val getTotalByPeriodUseCase: GetTotalByPeriodUseCase
): ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())

    init {
        viewModelScope.launch {
            getTotalByPeriod(_homeState.value.periodFilter)
        }
    }

    private val _totalByTypeFlow = meatRepository.getTotalWeightByType().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyMap()
    )

    val homeState = combine(_homeState,_totalByTypeFlow) { a, b ->
        HomeState(
            periodFilter = a.periodFilter,
            totalByPeriod = a.totalByPeriod,
            totalByMeatType = b,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        HomeState()
    )

    fun onFilterClick(period: TimePeriod) {
        viewModelScope.launch {
            getTotalByPeriod(period)
        }
    }

    suspend fun getTotalByPeriod(period: TimePeriod) {
        getTotalByPeriodUseCase(period).collect {
            _homeState.value = HomeState(
                periodFilter = period,
                totalByPeriod = if (it.isNotEmpty()) mapIntPeriodToString(it) else emptyMap()
            )
        }
    }

    private fun mapIntPeriodToString(map: Map<String, Float>): Map<String, Float> {
        val max = map.maxBy { it.value }
        return if (max.value >= 1000) map.mapValues { it.value / 1000 } else map
    }
}
