package com.currencies.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun childScope() = ViewModelChildScopeDelegate()

class ViewModelChildScopeDelegate : ReadOnlyProperty<ViewModel, CoroutineScope> {

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): CoroutineScope {
        val parentJob = thisRef.viewModelScope.coroutineContext.job
        return CoroutineScope(SupervisorJob(parentJob) + Dispatchers.Main.immediate)
    }
}
