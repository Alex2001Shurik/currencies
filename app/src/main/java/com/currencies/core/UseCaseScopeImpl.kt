package com.currencies.core

import kotlinx.coroutines.CoroutineDispatcher

class UseCaseScopeImpl(
    override val dispatcher: CoroutineDispatcher
) : UseCaseScope {

    override fun logException(exception: Throwable) {
        // log to Firebase, for example
    }

    override fun mapError(exception: Throwable): Error {
        return exception.toError
    }
}
