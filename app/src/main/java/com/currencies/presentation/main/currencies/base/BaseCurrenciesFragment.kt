package com.currencies.presentation.main.currencies.base

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.currencies.R
import com.currencies.databinding.FragmentCurrenciesBinding
import com.currencies.domain.entity.ICurrency
import com.currencies.presentation.main.MainNavigator
import com.currencies.presentation.main.ToolbarOptions
import com.currencies.presentation.main.callback.fragment.ChildrenGetSearchQueryCallback
import com.currencies.presentation.main.callback.fragment.ChildrenOnTextChangedCallback
import com.currencies.presentation.main.callback.fragment.ChildrenUpdateToolbarCallback
import com.currencies.presentation.main.currencies.base.adapter.CurrenciesDividerDecoration
import com.currencies.presentation.main.currencies.base.adapter.CurrencyCallback
import com.currencies.utils.observe
import com.currencies.utils.showError
import com.currencies.utils.view_binding.viewBinding
import org.koin.android.ext.android.inject

abstract class BaseCurrenciesFragment<T : ICurrency> :
    Fragment(R.layout.fragment_currencies),
    ChildrenUpdateToolbarCallback,
    ChildrenOnTextChangedCallback,
    ChildrenGetSearchQueryCallback,
    CurrencyCallback {

    protected val binding by viewBinding(FragmentCurrenciesBinding::bind)

    private val mainNavigator by inject<MainNavigator>()

    abstract val viewModel: CurrenciesViewModel<T>
    abstract val adapterClass: RecyclerView.Adapter<out RecyclerView.ViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTextChanged(getSearchQuery())
        updateToolbar(ToolbarOptions.SearchOptions)

        with(binding.rvCurrencies) {
            adapter = adapterClass
            addItemDecoration(CurrenciesDividerDecoration(requireContext()))
        }

        observe(viewModel.currencies) {
            with(binding) {
                tvNoResults.isVisible = it.isEmpty()
                includeShimmer.root.isGone = true
            }
        }

        observe(viewModel.error) {
            with(binding) {
                tvNoResults.isGone = true
                includeShimmer.root.isGone = true
            }
            showError(it)
        }
    }

    override fun onItem(currencyName: String) {
        mainNavigator.openConvertPage(currencyName, viewModel.localCurrencyName)
    }

    override fun onTextChanged(query: String?) {
        viewModel.search(query)
    }

    override fun onAddCurrency(currencyName: String) {
        viewModel.addCurrency(currencyName)
    }

    override fun onRemoveCurrency(currencyName: String) {
        viewModel.removeCurrency(currencyName)
    }
}
