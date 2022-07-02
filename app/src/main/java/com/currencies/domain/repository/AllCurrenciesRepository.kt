package com.currencies.domain.repository

import com.currencies.domain.entity.Currency
import kotlinx.coroutines.flow.Flow

interface AllCurrenciesRepository {
    suspend fun getAndSaveCurrencies(): List<Currency>

    fun subscribe(): Flow<List<Currency>>
}
