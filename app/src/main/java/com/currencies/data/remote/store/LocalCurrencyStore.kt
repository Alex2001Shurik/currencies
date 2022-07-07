package com.currencies.data.remote.store

import java.text.NumberFormat
import java.util.Locale

class LocalCurrencyStore {

    val localCurrency get() = NumberFormat.getCurrencyInstance(Locale.getDefault()).let {
        it.currency?.currencyCode.orEmpty()
    }
}
