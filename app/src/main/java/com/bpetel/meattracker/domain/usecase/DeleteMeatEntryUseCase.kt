package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.domain.LocalRepository

class DeleteMeatEntryUseCase(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(meat: Meat) {
        repository.delete(meat = meat)
    }
}