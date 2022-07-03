package com.currencies.presentation.main.callback

import androidx.fragment.app.FragmentManager

interface NotifyOnSearchTextChangedCallback {

    fun getSupportFragmentManager(): FragmentManager

    fun onTextChanged(query: String?) {
        getSupportFragmentManager().fragments.forEach {
            if (it is OnSearchTextChangedCallback) it.onTextChanged(query)
        }
    }
}
