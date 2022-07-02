package com.currencies.data.local.mapper

import com.currencies.data.local.entity.AllCurrencyDB
import com.currencies.domain.entity.Currency

fun List<AllCurrencyDB>.toDomain() = map { it.toDomain() }

private fun AllCurrencyDB.toDomain() = Currency(currencyName)

fun Currency.toAllCurrencyDb() = AllCurrencyDB(currencyName)
