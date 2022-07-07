package com.currencies.presentation.main.currencies.my_currencies.adapter

import com.currencies.databinding.ViewHolderMyCurrencyBinding
import com.currencies.domain.entity.Currency
import com.currencies.presentation.main.currencies.base.adapter.BaseCurrenciesViewHolder

class MyCurrenciesViewHolder(
    val binding: ViewHolderMyCurrencyBinding
) : BaseCurrenciesViewHolder<Currency>(binding) {

    override fun bind(currency: Currency) = with(binding) {
        tvCurrency.text = currency.currencyName
    }
}
