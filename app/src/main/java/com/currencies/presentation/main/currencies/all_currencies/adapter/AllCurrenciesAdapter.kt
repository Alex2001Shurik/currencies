package com.currencies.presentation.main.currencies.all_currencies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.currencies.databinding.ViewHolderCurrencyBinding
import com.currencies.domain.entity.AllCurrency
import com.currencies.presentation.main.currencies.base.BaseCurrenciesAdapter
import com.currencies.presentation.main.currencies.base.CurrencyCallback
import com.currencies.utils.debounceIn
import com.currencies.utils.inflater
import kotlinx.coroutines.CoroutineScope

class AllCurrenciesAdapter(
    private val scope: CoroutineScope,
    private val callback: CurrencyCallback
) : BaseCurrenciesAdapter<AllCurrency, AllCurrenciesViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCurrenciesViewHolder {
        return AllCurrenciesViewHolder(
            ViewHolderCurrencyBinding.inflate(
                parent.inflater,
                parent,
                false
            )
        ).apply {
            binding.root.setOnClickListener {
                callback.onItem(getItem(adapterPosition).currencyName)
            }
            binding.toggleAction.debounceIn(scope) {
                if (it) {
                    callback.onAddCurrency(getItem(adapterPosition).currencyName)
                } else {
                    callback.onRemoveCurrency(getItem(adapterPosition).currencyName)
                }
            }
        }
    }

    override fun onBindViewHolder(
        holder: AllCurrenciesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when {
            payloads.isNotEmpty() -> holder.onPayloads(payloads)
            else -> onBindViewHolder(holder, position)
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<AllCurrency>() {
        override fun areItemsTheSame(oldItem: AllCurrency, newItem: AllCurrency): Boolean {
            return oldItem.currencyName == newItem.currencyName
        }

        override fun areContentsTheSame(oldItem: AllCurrency, newItem: AllCurrency): Boolean {
            return oldItem.isAdded == newItem.isAdded
        }

        override fun getChangePayload(oldItem: AllCurrency, newItem: AllCurrency): Any? {
            return when {
                oldItem.isAdded != newItem.isAdded -> IsAddedPayload(newItem.isAdded)
                else -> null
            }
        }
    }
}
