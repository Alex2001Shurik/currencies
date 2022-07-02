package com.currencies.presentation.main

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.currencies.R
import com.currencies.presentation.main.convert.ConvertFragment
import com.currencies.presentation.main.currencies.all_currencies.AllCurrenciesFragment
import com.currencies.presentation.main.currencies.my_currencies.MyCurrenciesFragment

class MainNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerViewId: Int,
) {

    enum class Page(val id: Int) {
        AllCurrencies(R.id.tabAllCurrencies),
        MyCurrencies(R.id.tabMyCurrencies);

        companion object {
            fun from(@IdRes id: Int) = values().firstOrNull { it.id == id }
        }
    }

    private fun getFragment(page: Page): Fragment = when (page) {
        Page.AllCurrencies -> AllCurrenciesFragment.newInstance()
        Page.MyCurrencies -> MyCurrenciesFragment.newInstance()
    }

    private fun getFragmentsExcept(page: Page): List<Fragment> {
        val tags = Page.values().filterNot { it == page }.map { it.name }
        return fragmentManager.fragments.filter { tags.contains(it.tag) }
    }

    private fun openCurrencies(page: Page) {
        val transaction = fragmentManager.beginTransaction()

        val fragment: Fragment? = fragmentManager.findFragmentByTag(page.name)
        if (fragment == null) {
            transaction.add(containerViewId, getFragment(page), page.name)
        } else {
            transaction.show(fragment)
        }

        getFragmentsExcept(page).forEach { transaction.hide(it) }

        transaction.commitNow()
    }

    fun onTabSelected(item: MenuItem): Boolean {
        val page = Page.from(item.itemId)
        page?.let { openCurrencies(it) }
        return true
    }

    fun openAllCurrenciesTab() {
        openCurrencies(Page.AllCurrencies)
    }

    fun openConvertPage() {
        fragmentManager.beginTransaction()
            .add(containerViewId, ConvertFragment.newInstance())
            .addToBackStack(ConvertFragment::class.java.simpleName)
            .commit()
    }

    fun onBackPressed() {
        fragmentManager.popBackStack()
    }
}
