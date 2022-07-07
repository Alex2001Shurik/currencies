package com.currencies.presentation.main.currencies.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.currencies.domain.entity.ICurrency

abstract class BaseCurrenciesViewHolder<T : ICurrency>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(currency: T)
}
