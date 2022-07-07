package com.currencies.domain.repository

import com.currencies.data.remote.store.ConvertStore
import com.currencies.data.repository.ConvertRepository

class ConvertRepositoryImpl(
    private val convertStore: ConvertStore
) : ConvertRepository {

    override suspend fun convert(from: String, to: String, amount: Double): Double {
        return convertStore.getConvertResult(from, to, amount)
    }
}
