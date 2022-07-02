package com.currencies.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes id: Int) =
    Toast.makeText(this, id, Toast.LENGTH_SHORT).show()

fun Context.showToast(string: String) =
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
