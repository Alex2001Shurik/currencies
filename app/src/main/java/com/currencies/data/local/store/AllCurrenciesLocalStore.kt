package com.currencies.data.local.store

import com.currencies.data.local.dao.AllCurrenciesDao
import com.currencies.data.local.mapper.toAllCurrencyDb
import com.currencies.data.local.mapper.toDomain
import com.currencies.domain.entity.Currency
import kotlinx.coroutines.flow.map

class AllCurrenciesLocalStore(
    private val allCurrenciesDao: AllCurrenciesDao
) {

    fun getAllFlow() = allCurrenciesDao.getAll().map { it.toDomain() }

    suspend fun putAll(currencies: List<Currency>) {
        allCurrenciesDao.putAll(currencies.map { it.toAllCurrencyDb() })
    }
}
