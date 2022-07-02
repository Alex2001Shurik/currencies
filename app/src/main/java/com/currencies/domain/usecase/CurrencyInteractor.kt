package com.currencies.domain.usecase

import com.currencies.core.UseCase
import com.currencies.core.UseCaseScope
import com.currencies.domain.entity.AllCurrency
import com.currencies.domain.entity.Currency
import com.currencies.domain.repository.AllCurrenciesRepository
import com.currencies.domain.repository.MyCurrenciesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine

class CurrencyInteractor(
    scope: UseCaseScope,
    private val allCurrencyRepository: AllCurrenciesRepository,
    private val myCurrenciesRepository: MyCurrenciesRepository
) : UseCase(scope) {

    private val currencies = MutableSharedFlow<List<Currency>>(replay = 1)

    fun subscribe() = currencies.combine(
        myCurrenciesRepository.subscribe()
    ) { allCurrencies, myCurrencies ->
        allCurrencies.map {
            AllCurrency(
                currencyName = it.currencyName,
                isAdded = myCurrencies.contains(it)
            )
        }
    }.wrap()

    suspend fun getCurrencies() = wrapResult {
        currencies.emit(allCurrencyRepository.getAndSaveCurrencies())
    }

    suspend fun addCurrency(currencyName: String) = wrapResult {
        myCurrenciesRepository.addCurrency(currencyName)
    }

    suspend fun removeCurrency(currencyName: String) = wrapResult {
        myCurrenciesRepository.removeCurrency(currencyName)
    }
}
