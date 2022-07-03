package com.currencies.data.remote

import com.currencies.data.remote.entity.GetConvertResultResponse
import com.currencies.data.remote.entity.GetLatestCurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/latest")
    suspend fun getLatestCurrencies(): GetLatestCurrenciesResponse

    @GET("/convert?from=USD&to=EUR&amount=1200")
    suspend fun getConvertAmount(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): GetConvertResultResponse
}
