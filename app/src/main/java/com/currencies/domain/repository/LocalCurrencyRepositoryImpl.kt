package com.currencies.domain.repository

import com.currencies.data.remote.store.LocalCurrencyStore
import com.currencies.data.repository.LocalCurrencyRepository

class LocalCurrencyRepositoryImpl(
    private val localCurrencyStore: LocalCurrencyStore
) : LocalCurrencyRepository {

    override val localCurrencyName get() = localCurrencyStore.localCurrency
}
