package com.currencies.domain.usecase

import com.currencies.core.UseCase
import com.currencies.core.UseCaseScope
import com.currencies.data.repository.MyCurrenciesRepository
import com.currencies.domain.entity.Currency
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine

class MyCurrenciesInteractor(
    scope: UseCaseScope,
    private val myCurrenciesRepository: MyCurrenciesRepository
) : UseCase(scope), ICurrencyInteractor<Currency> {

    private var currencyQuery = MutableSharedFlow<String>(replay = 1)

    override suspend fun init() = wrapResult {
        currencyQuery.emit("")
    }

    override fun subscribe() =
        currencyQuery.combine(myCurrenciesRepository.subscribe()) { query, updatedCurrencies ->
            updatedCurrencies.filter {
                it.currencyName.contains(query, ignoreCase = true)
            }.sortedBy { it.currencyName }
        }.wrap()

    override suspend fun addCurrency(currencyName: String) = wrapResult {
        myCurrenciesRepository.addCurrency(currencyName)
    }

    override suspend fun removeCurrency(currencyName: String) = wrapResult {
        myCurrenciesRepository.removeCurrency(currencyName)
    }

    override suspend fun searchCurrencies(query: String) = wrapResult {
        currencyQuery.emit(query)
    }
}
