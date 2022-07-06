package com.currencies.data.repository

import com.currencies.domain.entity.Currency
import kotlinx.coroutines.flow.Flow

interface MyCurrenciesRepository {

    fun subscribe(): Flow<List<Currency>>

    suspend fun addCurrency(currencyName: String)

    suspend fun removeCurrency(currencyName: String)
}
