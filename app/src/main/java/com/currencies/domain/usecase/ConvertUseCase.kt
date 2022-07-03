package com.currencies.domain.usecase

import com.currencies.core.UseCase
import com.currencies.core.UseCaseScope
import com.currencies.domain.repository.ConvertRepository

class ConvertUseCase(
    scope: UseCaseScope,
    private val convertRepository: ConvertRepository
) : UseCase(scope) {

    suspend fun convert(from: String, amount: Double) = wrapResult {
        convertRepository.convert(from, amount)
    }
}
