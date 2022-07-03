package com.currencies.domain.repository

import com.currencies.data.remote.store.ConvertStore

class ConvertRepositoryImpl(
    private val convertStore: ConvertStore
) : ConvertRepository {
    override suspend fun convert(from: String, amount: Double): Double {
        // todo get local currency
        return convertStore.getConvertResult(from, to = "EUR", amount)
    }
}
