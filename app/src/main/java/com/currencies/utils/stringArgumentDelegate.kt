package com.currencies.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun stringArgument() = FragmentArgStringDelegate()

class FragmentArgStringDelegate : ReadWriteProperty<Fragment, String> {

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: String) {
        val arguments = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        arguments.putString(key, value)
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): String =
        thisRef.requireArguments().getString(property.name).orEmpty()
}
