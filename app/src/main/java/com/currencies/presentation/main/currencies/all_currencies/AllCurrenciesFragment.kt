package com.currencies.presentation.main.currencies.all_currencies

import android.os.Bundle
import android.view.View
import com.currencies.R
import com.currencies.presentation.main.currencies.BaseCurrenciesFragment
import com.currencies.utils.observe
import com.currencies.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCurrenciesFragment : BaseCurrenciesFragment() {

    override val titleRes get() = R.string.all_currencies

    private val viewModel by viewModel<AllCurrenciesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.allCurrency) {
            requireContext().showToast("${it.map { it.currencyName + it.isAdded}}")
        }
    }

    companion object {
        fun newInstance() = AllCurrenciesFragment()
    }
}
