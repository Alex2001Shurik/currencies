package com.currencies.presentation.main.currencies.all_currencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.currencies.core.BaseViewModel
import com.currencies.domain.entity.AllCurrency
import com.currencies.domain.usecase.CurrencyInteractor
import kotlinx.coroutines.launch

class AllCurrenciesViewModel(
    private val allCurrencyInteractor: CurrencyInteractor
) : BaseViewModel() {

    init {
        fetchCurrencies()
        subscribe()
    }

    val allCurrency = MutableLiveData<List<AllCurrency>>()

    private fun fetchCurrencies() {
        viewModelScope.launch {
            allCurrencyInteractor.getCurrencies()
                .onFailure { error.value = it }
        }
    }

    private fun subscribe() {
        viewModelScope.launch {
            allCurrencyInteractor.subscribe().collect { result ->
                result.onSuccess { allCurrency.value = it }
                result.onFailure { error.value = it }
            }
        }
    }
}
