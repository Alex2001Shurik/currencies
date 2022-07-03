package com.currencies.utils

import android.widget.ToggleButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ToggleButton.debounceIn(scope: CoroutineScope, timeout: Long = 200L, block: (Boolean) -> Unit) {
    callbackFlow {
        setOnClickListener { trySend(isChecked) }
        awaitClose { setOnClickListener { } }
    }.debounce(timeout).onEach { block(it) }.launchIn(scope)
}
