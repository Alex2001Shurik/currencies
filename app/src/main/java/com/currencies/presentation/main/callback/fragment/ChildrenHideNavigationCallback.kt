package com.currencies.presentation.main.callback.fragment

import androidx.fragment.app.FragmentActivity
import com.currencies.presentation.main.callback.activity.ParentHideNavigationCallback

interface ChildrenHideNavigationCallback {

    fun requireActivity(): FragmentActivity

    fun hideNavigation() {
        (requireActivity() as ParentHideNavigationCallback).hideNavigation()
    }
}
