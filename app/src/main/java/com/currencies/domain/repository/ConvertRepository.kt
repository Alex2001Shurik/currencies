package com.currencies.domain.repository

interface ConvertRepository {
    suspend fun convert(from: String, amount: Double): Double
}
