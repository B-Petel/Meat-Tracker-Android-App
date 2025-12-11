package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.domain.model.Meat
import com.bpetel.meattracker.domain.repository.MeatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GetAllMeatGroupByDateUseCase(
    private val repository: MeatRepository
) {
    operator fun invoke(): Flow<Map<LocalDate, List<Meat>>> {
        return repository.getAllMeat().map {
            it.groupBy { meat -> meat.localDate }
        }
    }
}