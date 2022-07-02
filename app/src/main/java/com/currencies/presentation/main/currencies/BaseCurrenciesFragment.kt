package com.currencies.presentation.main.currencies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.databinding.FragmentCurrenciesBinding
import com.currencies.presentation.main.MainNavigator
import com.currencies.utils.view_binding.viewBinding
import org.koin.android.ext.android.inject

abstract class BaseCurrenciesFragment : Fragment(R.layout.fragment_currencies) {

    val binding by viewBinding(FragmentCurrenciesBinding::bind)

    val mainNavigator by inject<MainNavigator>()

    abstract val titleRes: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainToolbar.tvTitle.setText(titleRes)
    }
}
