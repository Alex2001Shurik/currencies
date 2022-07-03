package com.currencies.presentation.main.currencies.base

interface CurrencyCallback : OnCurrencyClickCallback {
    fun onAddCurrency(currencyName: String)
    fun onRemoveCurrency(currencyName: String)
}

interface OnCurrencyClickCallback {
    fun onItem(currencyName: String)
}
