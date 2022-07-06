package com.currencies.domain.usecase

import com.currencies.core.UseCase
import com.currencies.core.UseCaseScope
import com.currencies.data.repository.AllCurrenciesRepository
import com.currencies.data.repository.MyCurrenciesRepository
import com.currencies.domain.entity.AllCurrency
import com.currencies.domain.entity.Currency
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine

class AllCurrenciesInteractor(
    scope: UseCaseScope,
    private val allCurrencyRepository: AllCurrenciesRepository,
    private val myCurrenciesRepository: MyCurrenciesRepository
) : UseCase(scope), ICurrencyInteractor<AllCurrency> {

    private val currencies = MutableSharedFlow<List<Currency>>(replay = 1)

    override suspend fun init() = wrapResult {
        currencies.emit(allCurrencyRepository.getAndSaveCurrencies())
    }

    override fun subscribe() = currencies.combine(
        myCurrenciesRepository.subscribe()
    ) { allCurrencies, myCurrencies ->
        allCurrencies.map {
            AllCurrency(
                currencyName = it.currencyName,
                isAdded = myCurrencies.contains(it)
            )
        }
    }.wrap()

    override suspend fun addCurrency(currencyName: String) = wrapResult {
        myCurrenciesRepository.addCurrency(currencyName)
    }

    override suspend fun removeCurrency(currencyName: String) = wrapResult {
        myCurrenciesRepository.removeCurrency(currencyName)
    }

    override suspend fun searchCurrencies(query: String) = wrapResult {
        currencies.emit(allCurrencyRepository.searchCurrencies(query))
    }
}
