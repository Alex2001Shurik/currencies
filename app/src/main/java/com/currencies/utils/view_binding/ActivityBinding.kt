package com.currencies.utils.view_binding

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> Activity.viewBinding(
    crossinline inflate: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { inflate(layoutInflater) }
