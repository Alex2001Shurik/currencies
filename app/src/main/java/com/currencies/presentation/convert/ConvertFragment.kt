package com.currencies.presentation.convert

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.databinding.FragmentConvertBinding
import com.currencies.presentation.main.CurrenciesNavigator
import com.currencies.presentation.main.ShowHideNavigationCallback
import com.currencies.presentation.view_binding.viewBinding
import org.koin.android.ext.android.inject

class ConvertFragment : Fragment(R.layout.fragment_convert) {

    private val binding by viewBinding(FragmentConvertBinding::bind)

    private val bottomNavigationCallback by lazy { requireActivity() as ShowHideNavigationCallback }
    private val mainNavigator by inject<CurrenciesNavigator>()

    override fun onStart() {
        super.onStart()
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            bottomNavigationCallback.hideNavigation()
        }

        binding.convertToolbar.toolbarMain.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = onBackPressed()
    }

    private fun onBackPressed() {
        bottomNavigationCallback.showNavigation()
        mainNavigator.backToPreviousPage()
    }

    override fun onDestroy() {
        onBackPressedCallback.remove()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = ConvertFragment()
    }
}
