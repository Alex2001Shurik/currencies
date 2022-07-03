package com.currencies.presentation.main

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.currencies.R
import com.currencies.databinding.ActivityMainBinding
import com.currencies.presentation.main.callback.HideNavigationCallback
import com.currencies.presentation.main.callback.NotifyOnSearchTextChangedCallback
import com.currencies.presentation.main.callback.ToolbarManager
import com.currencies.presentation.main.callback.ToolbarOptions
import com.currencies.utils.view_binding.viewBinding
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class MainActivity :
    AppCompatActivity(),
    HideNavigationCallback,
    ToolbarManager,
    NotifyOnSearchTextChangedCallback {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val mainNavigationModule = module {
        single { MainNavigator(supportFragmentManager, R.id.container) }
    }

    private val mainNavigator by inject<MainNavigator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(mainNavigationModule)

        if (savedInstanceState == null) {
            mainNavigator.openAllCurrenciesTab()
        }

        setupToolbarActions()

        binding.bottomNavView.setOnItemSelectedListener(mainNavigator::onTabSelected)
    }

    override fun updateToolbar(toolbarOptions: ToolbarOptions) {
        when (toolbarOptions) {
            is ToolbarOptions.WithBackOptions -> setupConvertToolbar(toolbarOptions.title)
            is ToolbarOptions.SearchOptions -> setupCurrenciesToolbar()
        }
    }

    private fun setupCurrenciesToolbar() = with(binding.mainToolbar) {
        tvCenteredTitle.isGone = true
        ivBack.isGone = true
        searchView.isVisible = true
    }

    private fun setupConvertToolbar(@StringRes titleRes: Int) = with(binding.mainToolbar) {
        searchView.isGone = true
        ivBack.isVisible = true
        tvCenteredTitle.isVisible = true
        tvCenteredTitle.setText(titleRes)
    }

    private fun setupToolbarActions() = with(binding.mainToolbar) {
        ivBack.setOnClickListener {
            backToCurrencies()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                onTextChanged(newText)
                return true
            }
        })
    }

    override fun onBackPressed() = with(binding.mainToolbar) {
        when {
            ivBack.isVisible -> backToCurrencies()
            searchView.isFocused -> searchView.clearFocus()
            !searchView.isIconified -> searchView.onActionViewCollapsed()
            else -> super.onBackPressed()
        }
    }

    private fun backToCurrencies() {
        binding.bottomNavView.isVisible = true
        mainNavigator.onBackPressed()
        setupCurrenciesToolbar()
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
