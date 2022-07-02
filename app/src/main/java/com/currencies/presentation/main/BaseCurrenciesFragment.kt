package com.currencies.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.databinding.FragmentCurrenciesBinding
import com.currencies.presentation.view_binding.viewBinding
import org.koin.android.ext.android.inject

abstract class BaseCurrenciesFragment : Fragment(R.layout.fragment_currencies) {

    private val binding by viewBinding(FragmentCurrenciesBinding::bind)

    private val mainNavigator by inject<CurrenciesNavigator>()

    abstract val titleRes: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainToolbar.tvTitle.setText(titleRes)
    }
}
