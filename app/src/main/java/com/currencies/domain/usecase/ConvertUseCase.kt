package com.currencies.domain.usecase

import com.currencies.core.UseCase
import com.currencies.core.UseCaseScope
import com.currencies.data.repository.ConvertRepository

class ConvertUseCase(
    scope: UseCaseScope,
    private val convertRepository: ConvertRepository
) : UseCase(scope) {

    suspend fun launch(from: String, to: String, amount: Double) = wrapResult {
        convertRepository.convert(from, to, amount)
    }
}
