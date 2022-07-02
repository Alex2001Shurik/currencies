package com.currencies.presentation.main

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.currencies.R
import com.currencies.databinding.ActivityMainBinding
import com.currencies.presentation.view_binding.viewBinding
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class MainActivity : AppCompatActivity(), ShowHideNavigationCallback {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val mainNavigationModule = module {
        single { CurrenciesNavigator(supportFragmentManager, R.id.container) }
    }

    private val mainNavigator by inject<CurrenciesNavigator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(mainNavigationModule)

        if (savedInstanceState == null) {
            mainNavigator.openAllCurrenciesTab()
        }

        binding.bottomNavView.setOnItemSelectedListener(mainNavigator::onTabSelected)
    }

    override fun showNavigation() = with(binding.bottomNavView) {
        isVisible = true
        startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.view_show))
    }

    override fun hideNavigation() = with(binding.bottomNavView) {
        startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.view_hide))
        isGone = true
    }

    override fun onDestroy() {
        unloadKoinModules(mainNavigationModule)
        super.onDestroy()
    }
}