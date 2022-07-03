package com.currencies.utils

import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.core.Error

fun Fragment.showError(error: Error) = when (error) {
    is Error.General -> showToast(error.message ?: getString(R.string.unexpected))
    is Error.MissingConnectionError -> showToast(R.string.missing_connection)
    is Error.NotFound -> showToast(R.string.unexpected)
}
