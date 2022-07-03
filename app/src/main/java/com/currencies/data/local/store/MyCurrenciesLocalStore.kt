package com.currencies.data.local.store

import com.currencies.data.local.dao.MyCurrenciesDao
import com.currencies.data.local.entity.MyCurrencyDB
import com.currencies.data.local.mapper.toDomain
import kotlinx.coroutines.flow.map

class MyCurrenciesLocalStore(
    private val myCurrenciesDao: MyCurrenciesDao
) {

    fun getAllFlow() = myCurrenciesDao.getAllFlow().map { it.toDomain() }

    suspend fun put(currencyName: String) {
        myCurrenciesDao.addCurrency(MyCurrencyDB(currencyName))
    }

    suspend fun delete(currencyName: String) {
        myCurrenciesDao.removeCurrency(currencyName)
    }
}
