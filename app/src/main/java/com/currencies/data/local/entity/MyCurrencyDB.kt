package com.currencies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_currency")
data class MyCurrencyDB(
    @PrimaryKey(autoGenerate = false)
    val currencyName: String
)
