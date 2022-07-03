package com.currencies.data.remote.store

import com.currencies.data.remote.Api

class ConvertStore(
    private val api: Api
) {

    suspend fun getConvertResult(from: String, to: String, amount: Double): Double {
        return api.getConvertAmount(from, to, amount).result
    }
}
