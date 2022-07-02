package com.currencies.data.remote

import com.currencies.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> api(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun retrofit(converter: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(converter))
    .client(okHttpClient)
    .build()

fun httpClient(
    loggingInterceptor: LoggingInterceptor
): OkHttpClient = clientBuilder()
    .addInterceptor(loggingInterceptor)
    .build()

fun clientBuilder() = OkHttpClient.Builder()
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

private const val TIMEOUT = 30L
