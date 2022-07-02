package com.currencies.utils.view_binding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.ref.Reference
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> viewBinding(bind: (View) -> T) = FragmentViewBindingDelegate(bind)

class FragmentViewBindingDelegate<T : ViewBinding>(private val bind: (View) -> T) : ReadOnlyProperty<Fragment, T> {

    private var bindingReference: Reference<T>? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return bindingReference?.get()
            ?: bind(thisRef.requireView()).also { bindingReference = WeakReference(it) }
    }
}
