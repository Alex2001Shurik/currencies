package com.currencies.presentation.main.currencies.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.currencies.domain.entity.ICurrency

abstract class BaseCurrenciesAdapter<T : ICurrency, K : BaseCurrenciesViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, K>(diffCallback) {

    override fun onBindViewHolder(holder: K, position: Int) {
        holder.bind(getItem(position))
    }
}
