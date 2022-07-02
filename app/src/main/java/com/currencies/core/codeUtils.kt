package com.currencies.core

import retrofit2.HttpException
import java.net.UnknownHostException

const val CODE_NOT_FOUND = 404

val Throwable.isMissingConnection: Boolean
    get() = this is UnknownHostException

val Throwable.isNotFound: Boolean
    get() = this is HttpException && code() == CODE_NOT_FOUND

val Throwable.isNetworkError: Boolean
    get() = this is HttpException

val Throwable.networkMessage: String?
    get() = if (this is HttpException) message() else null
