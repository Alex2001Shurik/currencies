package com.currencies.presentation.main.convert

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.databinding.FragmentConvertBinding
import com.currencies.presentation.main.ToolbarOptions
import com.currencies.presentation.main.callback.fragment.ChildrenUpdateToolbarCallback
import com.currencies.utils.observe
import com.currencies.utils.showError
import com.currencies.utils.stringArgument
import com.currencies.utils.view_binding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ConvertFragment : Fragment(R.layout.fragment_convert), ChildrenUpdateToolbarCallback {

    private var currencyName by stringArgument()
    private val viewModel by viewModel<ConvertViewModel> { parametersOf(currencyName) }
    private val binding by viewBinding(FragmentConvertBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar(ToolbarOptions.WithBackOptions(R.string.convert))

        observe(viewModel.convertResult) {
            // show result
        }

        observe(viewModel.error) {
            // show retry logic
            showError(it)
        }
    }

    companion object {
        fun newInstance(currencyName: String) = ConvertFragment().apply {
            this.currencyName = currencyName
        }
    }
}
