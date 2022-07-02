package com.currencies.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor(isDebug: Boolean) : Interceptor {

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(
            when (isDebug) {
                true -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        )

    override fun intercept(chain: Interceptor.Chain): Response {
        return interceptor.intercept(chain)
    }
}
