package com.currencies.presentation.main.callback.activity

import androidx.fragment.app.FragmentManager
import com.currencies.presentation.main.callback.fragment.ChildrenOnTextChangedCallback

interface ParentOnTextChangedCallback {

    fun getSupportFragmentManager(): FragmentManager

    fun onTextChanged(query: String?) {
        getSupportFragmentManager().fragments.forEach {
            if (it is ChildrenOnTextChangedCallback) it.onTextChanged(query)
        }
    }
}
