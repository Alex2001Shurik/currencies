package com.currencies.presentation.main.currencies.all_currencies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.currencies.domain.entity.AllCurrency
import com.currencies.presentation.main.currencies.all_currencies.adapter.AllCurrenciesAdapter
import com.currencies.presentation.main.currencies.base.BaseCurrenciesFragment
import com.currencies.presentation.main.currencies.base.CurrenciesViewModel
import com.currencies.presentation.main.currencies.base.Qualifier
import com.currencies.presentation.main.currencies.base.adapter.CurrencyCallback
import com.currencies.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCurrenciesFragment : BaseCurrenciesFragment<AllCurrency>(), CurrencyCallback {

    override val viewModel by viewModel<CurrenciesViewModel<AllCurrency>>(Qualifier.AllCurrencies)
    override val adapterClass get() = currenciesAdapter

    private val currenciesAdapter = AllCurrenciesAdapter(lifecycleScope, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.currencies) {
            currenciesAdapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = AllCurrenciesFragment()
    }
}
