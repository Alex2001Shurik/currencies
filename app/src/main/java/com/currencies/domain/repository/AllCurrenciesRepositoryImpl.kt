package com.currencies.domain.repository

import com.currencies.data.local.store.AllCurrenciesLocalStore
import com.currencies.data.remote.store.CurrenciesCloudStore
import com.currencies.domain.entity.Currency

class AllCurrenciesRepositoryImpl(
    private val cloudCurrenciesStore: CurrenciesCloudStore,
    private val localCurrenciesStore: AllCurrenciesLocalStore
) : AllCurrenciesRepository {

    override suspend fun getAndSaveCurrencies(): List<Currency> {
        return cloudCurrenciesStore.getCurrencies().also {
            localCurrenciesStore.putAll(it)
        }
    }

    override suspend fun searchCurrencies(query: String): List<Currency> {
        return localCurrenciesStore.search(query)
    }
}
