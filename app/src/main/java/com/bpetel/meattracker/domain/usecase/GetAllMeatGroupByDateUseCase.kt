package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.domain.LocalRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetAllMeatGroupByDateUseCase(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<Map<LocalDate, List<Meat>>> {
        return repository.getAllMeatGroupByDate()
    }
}