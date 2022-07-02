package com.currencies.data.remote.store

import com.currencies.data.remote.Api
import com.currencies.data.remote.mapper.toDomain
import com.currencies.domain.entity.Currency

class CurrenciesCloudStore(
    private val api: Api
) {

    suspend fun getCurrencies(): List<Currency> {
        return api.getLatestCurrencies().toDomain()
    }
}
