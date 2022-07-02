package com.currencies.core

val Throwable.toError: Error
    get() = when {
        isMissingConnection -> Error.MissingConnectionError
        isNotFound -> Error.NotFound
        isNetworkError -> Error.General(networkMessage)
        else -> Error.General()
    }
