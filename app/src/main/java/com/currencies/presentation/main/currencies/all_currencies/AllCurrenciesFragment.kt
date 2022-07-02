package com.currencies.presentation.main.currencies.all_currencies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.currencies.R
import com.currencies.presentation.main.currencies.BaseCurrenciesFragment
import com.currencies.presentation.main.currencies.all_currencies.adapter.AllCurrenciesAdapter
import com.currencies.presentation.main.currencies.all_currencies.adapter.AllCurrenciesDividerDecoration
import com.currencies.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCurrenciesFragment : BaseCurrenciesFragment(), AllCurrenciesAdapter.Callback {

    override val titleRes get() = R.string.all_currencies

    private val allCurrenciesAdapter = AllCurrenciesAdapter(lifecycleScope, this)
    private val viewModel by viewModel<AllCurrenciesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvCurrencies) {
            adapter = allCurrenciesAdapter
            addItemDecoration(AllCurrenciesDividerDecoration(requireContext()))
        }

        observe(viewModel.allCurrency) {
            allCurrenciesAdapter.submitList(it)
        }
    }

    override fun onAddCurrency(currencyName: String) {
        viewModel.addCurrency(currencyName)
    }

    override fun onRemoveCurrency(currencyName: String) {
        viewModel.removeCurrency(currencyName)
    }

    override fun onItem(currencyName: String) {
        // todo set currencyName
        mainNavigator.openConvertPage()
    }

    companion object {
        fun newInstance() = AllCurrenciesFragment()
    }
}
