package com.currencies.core

import kotlinx.coroutines.CoroutineDispatcher

interface UseCaseScope {
    val dispatcher: CoroutineDispatcher
    fun logException(exception: Throwable)
    fun mapError(exception: Throwable): Error
}
