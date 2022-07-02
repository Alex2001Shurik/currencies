package com.currencies.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

open class UseCase(
    private val scope: UseCaseScope
) {
    protected suspend fun <T : Any> wrapResult(
        block: suspend () -> T
    ) = withContext(scope.dispatcher) {
        try {
            Result.Success(block())
        } catch (expected: Exception) {
            logException(expected)
            Result.Failure(scope.mapError(expected))
        }
    }

    protected fun <T : Any> Flow<T>.wrap(): Flow<Result<T>> {
        return map { result ->
            Result.Success(result)
        }.catch<Result<T>> {
            logException(it)
            emit(Result.Failure(scope.mapError(it)))
        }.flowOn(
            scope.dispatcher
        )
    }

    private fun logException(throwable: Throwable) {
        scope.logException(throwable)
    }
}
