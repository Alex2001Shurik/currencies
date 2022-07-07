package com.currencies.presentation.main.convert

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.databinding.FragmentConvertBinding
import com.currencies.presentation.main.ToolbarOptions
import com.currencies.presentation.main.callback.fragment.ChildrenHideNavigationCallback
import com.currencies.presentation.main.callback.fragment.ChildrenUpdateToolbarCallback
import com.currencies.utils.observe
import com.currencies.utils.showError
import com.currencies.utils.showKeyboard
import com.currencies.utils.stringArgument
import com.currencies.utils.view_binding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ConvertFragment :
    Fragment(R.layout.fragment_convert),
    ChildrenUpdateToolbarCallback,
    ChildrenHideNavigationCallback {

    private var fromCurrencyName by stringArgument()
    private var toCurrencyName by stringArgument()
    private val viewModel by viewModel<ConvertViewModel> {
        parametersOf(fromCurrencyName, toCurrencyName)
    }
    private val binding by viewBinding(FragmentConvertBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar(ToolbarOptions.WithBackOptions(R.string.convert))
        hideNavigation()

        setCurrenciesName()
        setupActions()
        observeLiveData()
    }

    private fun setCurrenciesName() = with(binding) {
        tvFromCurrency.text = fromCurrencyName
        tvToCurrency.text = toCurrencyName
    }

    private fun setupActions() = with(binding) {
        etAmount.requestFocus()
        showKeyboard(etAmount)
        etAmount.doAfterTextChanged(::convertAmount)

        tvRetry.setOnClickListener {
            tvRetry.isGone = true
            convertAmount(etAmount.editableText)
        }
    }

    private fun observeLiveData() = with(binding) {
        observe(viewModel.convertResult) {
            tvRetry.isGone = true
            tvResult.text = getString(R.string.format_float, it)
        }

        observe(viewModel.error) {
            tvRetry.isVisible = true
            showError(it)
        }
    }

    private fun convertAmount(editable: Editable?) {
        val amount = editable.toString().toDoubleOrNull()
        if (amount != null) {
            viewModel.convert(amount)
        } else {
            binding.tvResult.text = ""
        }
    }

    companion object {
        fun newInstance(fromCurrencyName: String, toCurrencyName: String) =
            ConvertFragment().apply {
                this.fromCurrencyName = fromCurrencyName
                this.toCurrencyName = toCurrencyName
            }
    }
}
