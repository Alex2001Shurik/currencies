package com.currencies.domain.usecase

import com.currencies.core.UseCase
import com.currencies.core.UseCaseScope
import com.currencies.data.repository.LocalCurrencyRepository

class GetLocalCurrencyUseCase(
    scope: UseCaseScope,
    private val localCurrencyRepository: LocalCurrencyRepository
) : UseCase(scope) {

    suspend fun launch() = wrapResult {
        localCurrencyRepository.localCurrencyName
    }
}
