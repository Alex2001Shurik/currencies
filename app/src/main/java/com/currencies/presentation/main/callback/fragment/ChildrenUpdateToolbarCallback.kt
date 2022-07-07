package com.currencies.presentation.main.callback.fragment

import androidx.fragment.app.FragmentActivity
import com.currencies.presentation.main.ToolbarOptions
import com.currencies.presentation.main.callback.activity.ParentUpdateToolbarCallback

interface ChildrenUpdateToolbarCallback {

    fun requireActivity(): FragmentActivity

    fun updateToolbar(toolbarOptions: ToolbarOptions) {
        (requireActivity() as ParentUpdateToolbarCallback).updateToolbar(toolbarOptions)
    }
}
