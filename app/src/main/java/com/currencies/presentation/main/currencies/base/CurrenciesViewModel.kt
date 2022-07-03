package com.currencies.presentation.main.currencies.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.currencies.core.BaseViewModel
import com.currencies.domain.entity.ICurrency
import com.currencies.domain.usecase.ICurrencyInteractor
import kotlinx.coroutines.launch

class CurrenciesViewModel<T : ICurrency>(
    private val currencyInteractor: ICurrencyInteractor<T>
) : BaseViewModel() {

    init {
        init()
        subscribe()
    }

    val currencies = MutableLiveData<List<T>>()

    private fun init() {
        viewModelScope.launch {
            currencyInteractor.init()
                .onFailure { error.value = it }
        }
    }

    private fun subscribe() {
        viewModelScope.launch {
            currencyInteractor.subscribe().collect { result ->
                result.onSuccess { currencies.value = it }
                result.onFailure { error.value = it }
            }
        }
    }

    fun removeCurrency(currencyName: String) {
        viewModelScope.launch {
            currencyInteractor.removeCurrency(currencyName)
                .onFailure { error.value = it }
        }
    }

    fun addCurrency(currencyName: String) {
        viewModelScope.launch {
            currencyInteractor.addCurrency(currencyName)
                .onFailure { error.value = it }
        }
    }

    fun search(query: String?) {
        viewModelScope.launch {
            currencyInteractor.searchCurrencies(query.orEmpty().trim())
                .onFailure { error.value = it }
        }
    }
}
