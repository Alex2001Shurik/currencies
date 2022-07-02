package com.currencies.presentation.main.currencies.my_currencies

import com.currencies.R
import com.currencies.presentation.main.currencies.BaseCurrenciesFragment

class MyCurrenciesFragment : BaseCurrenciesFragment() {

    override val titleRes get() = R.string.my_currencies

    companion object {
        fun newInstance() = MyCurrenciesFragment()
    }
}
