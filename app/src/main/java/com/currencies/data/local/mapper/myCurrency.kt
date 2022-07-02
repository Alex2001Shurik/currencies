package com.currencies.data.local.mapper

import com.currencies.data.local.entity.MyCurrencyDB
import com.currencies.domain.entity.Currency

fun List<MyCurrencyDB>.toDomain() = map { it.toDomain() }

fun MyCurrencyDB.toDomain() = Currency(currencyName)
