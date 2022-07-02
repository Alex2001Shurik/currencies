package com.currencies.presentation.main.all_currencies

import com.currencies.R
import com.currencies.presentation.main.BaseCurrenciesFragment

class AllCurrenciesFragment : BaseCurrenciesFragment() {

    override val titleRes get() = R.string.all_currencies

    companion object {
        fun newInstance() = AllCurrenciesFragment()
    }
}
