package com.currencies.data.remote.entity

data class GetLatestCurrenciesResponse(
    val rates: Map<String, Double>
)
