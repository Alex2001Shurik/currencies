package com.currencies.presentation.main.callback.fragment

import androidx.fragment.app.FragmentActivity
import com.currencies.presentation.main.callback.activity.ParentGetSearchQueryCallback

interface ChildrenGetSearchQueryCallback {

    fun requireActivity(): FragmentActivity

    fun getSearchQuery(): String {
        return (requireActivity() as ParentGetSearchQueryCallback).getSearchQuery()
    }
}
