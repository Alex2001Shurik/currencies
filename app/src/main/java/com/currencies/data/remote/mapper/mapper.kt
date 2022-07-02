package com.currencies.data.remote.mapper

import com.currencies.data.remote.entity.GetLatestCurrenciesResponse
import com.currencies.domain.entity.Currency

fun GetLatestCurrenciesResponse.toDomain() = rates.keys.map { Currency(it) }
