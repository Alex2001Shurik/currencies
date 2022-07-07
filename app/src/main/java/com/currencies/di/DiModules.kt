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
import com.currencies.data.remote.store.ConvertStore
import com.currencies.data.remote.store.CurrenciesCloudStore
import com.currencies.data.remote.store.LocalCurrencyStore
import com.currencies.data.repository.AllCurrenciesRepository
import com.currencies.data.repository.ConvertRepository
import com.currencies.data.repository.LocalCurrencyRepository
import com.currencies.data.repository.MyCurrenciesRepository
import com.currencies.domain.repository.AllCurrenciesRepositoryImpl
import com.currencies.domain.repository.ConvertRepositoryImpl
import com.currencies.domain.repository.LocalCurrencyRepositoryImpl
import com.currencies.domain.repository.MyCurrenciesRepositoryImpl
import com.currencies.domain.usecase.AllCurrenciesInteractor
import com.currencies.domain.usecase.ConvertUseCase
import com.currencies.domain.usecase.GetLocalCurrencyUseCase
import com.currencies.domain.usecase.MyCurrenciesInteractor
import com.currencies.presentation.main.convert.ConvertViewModel
import com.currencies.presentation.main.currencies.base.CurrenciesViewModel
import com.currencies.presentation.main.currencies.base.Qualifier
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
    single { ConvertStore(get()) }
    single { LocalCurrencyStore() }
}

private val repositoryModule = module {
    single { AllCurrenciesRepositoryImpl(get(), get()) } bind AllCurrenciesRepository::class
    single { MyCurrenciesRepositoryImpl(get()) } bind MyCurrenciesRepository::class
    single { ConvertRepositoryImpl(get()) } bind ConvertRepository::class
    single { LocalCurrencyRepositoryImpl(get()) } bind LocalCurrencyRepository::class
}

private val useCaseModule = module {
    single { Dispatchers.IO }
    single { UseCaseScopeImpl(get()) } bind UseCaseScope::class
    factory { AllCurrenciesInteractor(get(), get(), get()) }
    factory { MyCurrenciesInteractor(get(), get()) }
    factory { ConvertUseCase(get(), get()) }
    factory { GetLocalCurrencyUseCase(get(), get()) }
}

private val viewModelModule = module {
    viewModel(Qualifier.MyCurrencies) { CurrenciesViewModel(get<MyCurrenciesInteractor>(), get()) }
    viewModel(Qualifier.AllCurrencies) { CurrenciesViewModel(get<AllCurrenciesInteractor>(), get()) }
    viewModel { (fromCurrencyName: String, toCurrencyName: String) ->
        ConvertViewModel(fromCurrencyName, toCurrencyName, get())
    }
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
