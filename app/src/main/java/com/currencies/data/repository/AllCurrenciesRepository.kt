package com.currencies.data.repository

import com.currencies.domain.entity.Currency

interface AllCurrenciesRepository {
    suspend fun getAndSaveCurrencies(): List<Currency>

    suspend fun searchCurrencies(query: String): List<Currency>
}
