package com.bpetel.meattracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.domain.usecase.GetTotalByPeriodUseCase
import com.bpetel.meattracker.presentation.utils.TimePeriod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds


class HomeViewModel(
    meatRepository: MeatRepository,
    getTotalByPeriodUseCase: GetTotalByPeriodUseCase
): ViewModel() {
    private val _period = MutableStateFlow(TimePeriod.WEEK)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _totelByPeriod = _period
        .flatMapLatest { period ->
            getTotalByPeriodUseCase(period)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds),
            emptyMap()
        )

    private val _totalByTypeFlow = meatRepository.getTotalWeightByType().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        emptyMap()
    )

    val homeState = combine(_period, _totelByPeriod,_totalByTypeFlow) { a, b, c ->
        HomeState(
            periodFilter = a,
            totalByPeriod = b,
            totalByMeatType = c,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        HomeState()
    )

    fun onFilterClick(period: TimePeriod) {
        _period.value = period
    }
}
