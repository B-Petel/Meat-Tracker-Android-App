package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.presentation.utils.TimePeriod
import kotlinx.coroutines.flow.Flow

class GetTotalByPeriodUseCase(
    private val repository: MeatRepository
) {
    operator fun invoke(period: TimePeriod): Flow<Map<String, Float>> {
        return when (period) {
            TimePeriod.WEEK -> repository.getTotalWeightByWeek()
            TimePeriod.MONTH -> repository.getTotalWeightByMonth()
            TimePeriod.YEAR -> repository.getTotalWeightByYear()
        }
    }
}