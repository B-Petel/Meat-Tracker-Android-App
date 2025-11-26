package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.domain.LocalRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetTotalMeatWeightGroupByDateUseCase(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<Map<LocalDate, Int>> {
        return repository.getTotalMeatWeightByDate()
    }
}