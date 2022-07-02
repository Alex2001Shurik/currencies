package com.currencies.presentation.main.my_currencies

import com.currencies.R
import com.currencies.presentation.main.BaseCurrenciesFragment

class MyCurrenciesFragment : BaseCurrenciesFragment() {

    override val titleRes get() = R.string.my_currencies

    companion object {
        fun newInstance() = MyCurrenciesFragment()
    }
}
