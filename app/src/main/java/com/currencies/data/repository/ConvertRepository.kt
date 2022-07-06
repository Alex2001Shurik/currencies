package com.currencies.data.repository

interface ConvertRepository {
    suspend fun convert(from: String, to: String, amount: Double): Double
}
