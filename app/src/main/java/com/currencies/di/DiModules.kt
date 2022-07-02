package com.currencies.di

import android.app.Application
import com.currencies.BuildConfig
import com.currencies.core.UseCaseScope
import com.currencies.core.UseCaseScopeImpl
import com.currencies.data.local.CurrenciesDatabase
import com.currencies.data.local.store.AllCurrenciesLocalStore
import com.currencies.data.local.store.MyCurrenciesLocalStore
import com.currencies.data.remote.Api
import com.currencies.data.remote.LoggingInterceptor
import com.currencies.data.remote.api
import com.currencies.data.remote.httpClient
import com.currencies.data.remote.retrofit
import com.currencies.data.remote.store.CurrenciesCloudStore
import com.currencies.domain.repository.AllCurrenciesRepository
import com.currencies.domain.repository.AllCurrenciesRepositoryImpl
import com.currencies.domain.repository.MyCurrenciesRepository
import com.currencies.domain.repository.MyCurrenciesRepositoryImpl
import com.currencies.domain.usecase.CurrencyInteractor
import com.currencies.presentation.main.currencies.all_currencies.AllCurrenciesViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

private val networkModule = module {
    single { Gson() }
    single { LoggingInterceptor(BuildConfig.DEBUG) }
    single { api<Api>(retrofit(get(), httpClient(get()))) }
}

private val databaseModule = module {
    single { CurrenciesDatabase.getInstance(get()) }
    single { get<CurrenciesDatabase>().myCurrenciesDao() }
    single { get<CurrenciesDatabase>().allCurrenciesDao() }
}

private val storeModule = module {
    single { CurrenciesCloudStore(get()) }
    single { AllCurrenciesLocalStore(get()) }
    single { MyCurrenciesLocalStore(get()) }
}

private val repositoryModule = module {
    single { AllCurrenciesRepositoryImpl(get(), get()) } bind AllCurrenciesRepository::class
    single { MyCurrenciesRepositoryImpl(get()) } bind MyCurrenciesRepository::class
}

private val useCaseModule = module {
    single { Dispatchers.IO }
    single { UseCaseScopeImpl(get()) } bind UseCaseScope::class
    factory { CurrencyInteractor(get(), get(), get()) }
}

private val viewModelModule = module {
    viewModel { AllCurrenciesViewModel(get()) }
}

fun setupDependencyFramework(application: Application) {
    startKoin {
        androidContext(application)
        modules(
            networkModule,
            databaseModule,
            storeModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}
