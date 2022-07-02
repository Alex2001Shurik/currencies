package com.currencies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currencies.data.local.entity.AllCurrencyDB
import kotlinx.coroutines.flow.Flow

@Dao
interface AllCurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putAll(currencies: List<AllCurrencyDB>)

    @Query("select * from all_currency")
    fun getAll(): Flow<List<AllCurrencyDB>>
}
