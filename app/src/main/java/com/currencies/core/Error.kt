package com.currencies.core

sealed class Error {
    data class General(val message: String? = null) : Error()
    object MissingConnectionError : Error()
    object NotFound : Error()
}
