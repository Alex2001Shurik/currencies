package com.currencies.presentation.main.currencies.my_currencies

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.currencies.domain.entity.Currency
import com.currencies.presentation.main.currencies.base.BaseCurrenciesFragment
import com.currencies.presentation.main.currencies.base.CurrenciesViewModel
import com.currencies.presentation.main.currencies.base.Qualifier
import com.currencies.presentation.main.currencies.base.adapter.OnCurrencyClickCallback
import com.currencies.presentation.main.currencies.my_currencies.adapter.MyCurrenciesAdapter
import com.currencies.presentation.main.currencies.my_currencies.adapter.SwipeToDeleteCallback
import com.currencies.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyCurrenciesFragment : BaseCurrenciesFragment<Currency>(), OnCurrencyClickCallback {

    override val viewModel by viewModel<CurrenciesViewModel<Currency>>(Qualifier.MyCurrencies)
    override val adapterClass get() = currenciesAdapter

    private val currenciesAdapter = MyCurrenciesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSwipedDelete()

        observe(viewModel.currencies) {
            currenciesAdapter.submitList(it)
        }
    }

    private fun onSwipedDelete() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val currencyName = currenciesAdapter.currentList[position].currencyName
                viewModel.removeCurrency(currencyName)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvCurrencies)
    }

    companion object {
        fun newInstance() = MyCurrenciesFragment()
    }
}
