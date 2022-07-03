package com.currencies.presentation.main.currencies.base

import org.koin.ext.getFullName
import org.koin.core.qualifier.Qualifier as KoinQualifier

sealed class Qualifier : KoinQualifier {
    override val value get() = this::class.getFullName()

    object AllCurrencies : Qualifier()
    object MyCurrencies : Qualifier()
}
