package com.currencies.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.showKeyboard(view: View) {
    requireActivity().openKeyboard(view)
}

private fun Context.openKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = getSystemService(InputMethodManager::class.java)
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}
