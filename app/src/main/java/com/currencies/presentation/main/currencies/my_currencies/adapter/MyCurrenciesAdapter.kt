package com.currencies.presentation.main.currencies.my_currencies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.currencies.databinding.ViewHolderMyCurrencyBinding
import com.currencies.domain.entity.Currency
import com.currencies.presentation.main.currencies.base.BaseCurrenciesAdapter
import com.currencies.presentation.main.currencies.base.OnCurrencyClickCallback
import com.currencies.utils.inflater

class MyCurrenciesAdapter(
    private val callback: OnCurrencyClickCallback
) : BaseCurrenciesAdapter<Currency, MyCurrenciesViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCurrenciesViewHolder {
        return MyCurrenciesViewHolder(
            ViewHolderMyCurrencyBinding.inflate(
                parent.inflater,
                parent,
                false
            )
        ).apply {
            binding.root.setOnClickListener {
                callback.onItem(getItem(adapterPosition).currencyName)
            }
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.currencyName == newItem.currencyName
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }
    }
}
