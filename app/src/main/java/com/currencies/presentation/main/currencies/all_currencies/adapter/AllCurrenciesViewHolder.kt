package com.currencies.presentation.main.currencies.all_currencies.adapter

import com.currencies.databinding.ViewHolderCurrencyBinding
import com.currencies.domain.entity.AllCurrency
import com.currencies.presentation.main.currencies.base.BaseCurrenciesViewHolder

class AllCurrenciesViewHolder(
    val binding: ViewHolderCurrencyBinding
) : BaseCurrenciesViewHolder<AllCurrency>(binding) {

    override fun bind(currency: AllCurrency) = with(binding) {
        setIsAdded(currency.isAdded)
        tvCurrency.text = currency.currencyName
    }

    fun onPayloads(payloads: MutableList<Any>) {
        payloads.forEach {
            if (it is IsAddedPayload) setIsAdded(it.isAdded)
        }
    }

    private fun setIsAdded(isAdded: Boolean) {
        binding.toggleAction.isChecked = isAdded
    }
}
