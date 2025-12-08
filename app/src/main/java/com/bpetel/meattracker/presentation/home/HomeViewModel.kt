package com.bpetel.meattracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.presentation.utils.TimePeriod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import kotlin.time.Duration.Companion.seconds

class HomeViewModel(
    meatRepository: MeatRepository
): ViewModel() {
    val e = meatRepository.getTotalByType().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyMap()
    )

    val week = mapOf(
        1 to 200,
        2 to 100,
        4 to 400
    )

    val month = mapOf(
        1 to 200,
        2 to 100,
        4 to 400,
        11 to 200,
        16 to 100,
        20 to 400,
        23 to 200,
        26 to 100,
        30 to 400
    )

    val year = mapOf(
        1 to 200,
        2 to 200,
        4 to 400,
        5 to 300,
        7 to 100,
        8 to 400,
        9 to 600,
        11 to 100
    )

    val currentDay = LocalDate.now().dayOfWeek.value
    val currentMonthDay = LocalDate.now().dayOfMonth
    val currentMonth = LocalDate.now().month.value
    val weekData = (0..currentDay-1).associateWith { day -> week[day] ?: 0 }
    val monthData = (0..currentMonthDay-1).associateWith { day -> month[day] ?: 0 }
    val yearData = (0..currentMonth-1).associateWith { month -> year[month] ?: 0 }

    private val _homeState = MutableStateFlow(HomeState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = _homeState.flatMapLatest { period ->
        when(period.period) {
            TimePeriod.WEEK ->  MutableStateFlow(HomeState(period.period, weekData))// //groupByDayUseCase.invoke()
            TimePeriod.MONTH -> MutableStateFlow(HomeState(period.period, monthData))  //groupByWeekUseCase.invoke()
            TimePeriod.YEAR -> MutableStateFlow(HomeState(period.period, yearData)) //groupByMonthUseCase.invoke()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        HomeState()
    )

    fun onFilterClick(period: TimePeriod) {
        _homeState.value = HomeState(period)
    }

    suspend fun getType(flow: Flow<Map<String, Int>>): Map<String, Int> {
        flow.collect {
            return@collect
        }
        return emptyMap()
    }
}
