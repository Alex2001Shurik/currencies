package com.currencies.domain.entity

interface ICurrency {
    val currencyName: String
}

data class Currency(
    override val currencyName: String
) : ICurrency

data class AllCurrency(
    override val currencyName: String,
    val isAdded: Boolean
) : ICurrency
