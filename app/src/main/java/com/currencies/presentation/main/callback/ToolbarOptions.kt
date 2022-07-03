package com.currencies.presentation.main.callback

import androidx.annotation.StringRes

sealed class ToolbarOptions {
    object SearchOptions : ToolbarOptions()
    data class WithBackOptions(@StringRes val title: Int) : ToolbarOptions()
}
