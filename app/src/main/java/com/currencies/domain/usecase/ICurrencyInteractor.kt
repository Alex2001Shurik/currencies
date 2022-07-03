package com.currencies.domain.usecase

import com.currencies.domain.entity.ICurrency
import kotlinx.coroutines.flow.Flow
import com.currencies.core.Result as CurrencyResult

interface ICurrencyInteractor<T : ICurrency> {

    fun subscribe(): Flow<CurrencyResult<List<T>>>

    suspend fun init(): CurrencyResult<Unit>

    suspend fun addCurrency(currencyName: String): CurrencyResult<Unit>

    suspend fun removeCurrency(currencyName: String): CurrencyResult<Unit>

    suspend fun searchCurrencies(query: String): CurrencyResult<Unit>
}
