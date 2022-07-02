package com.currencies.data.remote

import com.currencies.data.remote.entity.GetLatestCurrenciesResponse
import retrofit2.http.GET

interface Api {

    @GET("/latest")
    suspend fun getLatestCurrencies(): GetLatestCurrenciesResponse
}
