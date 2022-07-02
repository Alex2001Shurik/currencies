package com.currencies.presentation

import android.app.Application
import com.currencies.di.setupDependencyFramework
import org.koin.core.component.KoinComponent

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        setupDependencyFramework(this)
    }
}
