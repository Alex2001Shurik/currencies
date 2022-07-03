package com.currencies.presentation.main.currencies.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.currencies.R
import com.currencies.databinding.FragmentCurrenciesBinding
import com.currencies.domain.entity.ICurrency
import com.currencies.presentation.main.MainNavigator
import com.currencies.presentation.main.callback.HideNavigationCallback
import com.currencies.presentation.main.callback.OnSearchTextChangedCallback
import com.currencies.presentation.main.callback.ToolbarOptions
import com.currencies.presentation.main.callback.UpdateToolbarCallback
import com.currencies.utils.view_binding.viewBinding
import org.koin.android.ext.android.inject

abstract class BaseCurrenciesFragment<T : ICurrency> :
    Fragment(R.layout.fragment_currencies),
    UpdateToolbarCallback,
    OnSearchTextChangedCallback,
    CurrencyCallback {

    protected val binding by viewBinding(FragmentCurrenciesBinding::bind)

    private val mainNavigator by inject<MainNavigator>()
    private val bottomNavigationCallback by lazy { requireActivity() as HideNavigationCallback }

    abstract val viewModel: CurrenciesViewModel<T>
    abstract val adapterClass: RecyclerView.Adapter<out RecyclerView.ViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar(ToolbarOptions.SearchOptions)

        with(binding.rvCurrencies) {
            adapter = adapterClass
            addItemDecoration(CurrenciesDividerDecoration(requireContext()))
        }
    }

    override fun onItem(currencyName: String) {
        // todo set currencyName
        mainNavigator.openConvertPage()
        bottomNavigationCallback.hideNavigation()
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
