package com.currencies.presentation.main.convert

import androidx.lifecycle.MutableLiveData
import com.currencies.core.BaseViewModel
import com.currencies.domain.usecase.ConvertUseCase
import com.currencies.utils.childScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class ConvertViewModel(
    private val fromCurrencyName: String,
    private val toCurrencyName: String,
    private val convertUseCase: ConvertUseCase
) : BaseViewModel() {

    private val searchScope by childScope()

    val convertResult = MutableLiveData<Double>()

    fun convert(amount: Double) = with(searchScope) {
        coroutineContext.cancelChildren()
        launch {
            convertUseCase.launch(fromCurrencyName, toCurrencyName, amount)
                .fold(
                    onSuccess = convertResult::setValue,
                    onFailure = error::setValue
                )
        }
    }
}
