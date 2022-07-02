package com.currencies.presentation.main

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.currencies.R
import com.currencies.presentation.convert.ConvertFragment
import com.currencies.presentation.main.all_currencies.AllCurrenciesFragment
import com.currencies.presentation.main.my_currencies.MyCurrenciesFragment

class CurrenciesNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerViewId: Int,
) {

    private fun openCurrencies(fragment: BaseCurrenciesFragment) {
        fragmentManager.beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    fun onTabSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tabAllCurrencies -> openAllCurrenciesTab()
            R.id.tabMyCurrencies -> openMyCurrenciesTab()
        }
        return true
    }

    fun openAllCurrenciesTab() {
        openCurrencies(AllCurrenciesFragment.newInstance())
    }

    private fun openMyCurrenciesTab() {
        openCurrencies(MyCurrenciesFragment.newInstance())
    }

    fun openConvertPage() {
        fragmentManager.beginTransaction()
            .add(containerViewId, ConvertFragment.newInstance())
            .addToBackStack(ConvertFragment::class.java.simpleName)
            .commit()
    }

    fun backToPreviousPage() {
        fragmentManager.popBackStack()
    }
}
