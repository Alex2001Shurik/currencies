package com.currencies.core

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val error: Error) : Result<Nothing>()

    fun fold(onSuccess: (T) -> Unit, onFailure: (Error) -> Unit) {
        when (this) {
            is Success -> onSuccess(data)
            is Failure -> onFailure(error)
        }
    }

    fun onSuccess(onSuccess: (T) -> Unit) {
        if (this is Success) onSuccess(data)
    }

    fun onFailure(onFailure: (Error) -> Unit) {
        if (this is Failure) onFailure(error)
    }
}
