package com.currencies.domain.repository

import com.currencies.data.local.store.MyCurrenciesLocalStore
import com.currencies.data.repository.MyCurrenciesRepository

class MyCurrenciesRepositoryImpl(
    private val myCurrenciesLocalStore: MyCurrenciesLocalStore
) : MyCurrenciesRepository {

    override fun subscribe() = myCurrenciesLocalStore.getAllFlow()

    override suspend fun addCurrency(currencyName: String) {
        myCurrenciesLocalStore.put(currencyName)
    }

    override suspend fun removeCurrency(currencyName: String) {
        myCurrenciesLocalStore.delete(currencyName)
    }
}
