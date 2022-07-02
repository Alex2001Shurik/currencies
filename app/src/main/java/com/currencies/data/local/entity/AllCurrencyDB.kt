package com.currencies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_currency")
data class AllCurrencyDB(
    @PrimaryKey(autoGenerate = false)
    val currencyName: String
)
