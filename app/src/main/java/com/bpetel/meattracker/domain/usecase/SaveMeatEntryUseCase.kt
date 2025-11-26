package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.domain.LocalRepository

class SaveMeatEntryUseCase(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(
        id: Int?,
        meat: Meat
    ) {
        id?.let { repository.update(meat) } ?: repository.insert(meat)
    }
}