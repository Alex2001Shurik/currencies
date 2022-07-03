package com.currencies.presentation.main.callback

import androidx.fragment.app.FragmentActivity

interface UpdateToolbarCallback {

    fun requireActivity(): FragmentActivity

    fun updateToolbar(toolbarOptions: ToolbarOptions) {
        (requireActivity() as ToolbarManager).updateToolbar(toolbarOptions)
    }
}
