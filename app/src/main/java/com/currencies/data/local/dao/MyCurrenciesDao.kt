package com.currencies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currencies.data.local.entity.MyCurrencyDB
import kotlinx.coroutines.flow.Flow

@Dao
interface MyCurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrency(currency: MyCurrencyDB)

    @Query("delete from my_currency where currencyName = :currencyName")
    suspend fun removeCurrency(currencyName: String)

    @Query("select * from my_currency")
    fun getAllFlow(): Flow<List<MyCurrencyDB>>
}
