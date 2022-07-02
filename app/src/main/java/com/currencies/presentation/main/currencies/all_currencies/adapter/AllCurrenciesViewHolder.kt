package com.currencies.presentation.main.currencies.all_currencies.adapter

import androidx.recyclerview.widget.RecyclerView
import com.currencies.databinding.ViewHolderCurrencyBinding
import com.currencies.domain.entity.AllCurrency

class AllCurrenciesViewHolder(
    val binding: ViewHolderCurrencyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(allCurrency: AllCurrency) = with(binding) {
        setIsAdded(allCurrency.isAdded)
        tvCurrency.text = allCurrency.currencyName
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
