package com.currencies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currencies.data.local.entity.AllCurrencyDB

@Dao
interface AllCurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putAll(currencies: List<AllCurrencyDB>)

    @Query(SEARCH)
    suspend fun search(query: String, escaped: String = "%$query%"): List<AllCurrencyDB>

    companion object {
        private const val SEARCH =
            """
        select * from all_currency 
        where UPPER(currencyName) like UPPER(:escaped)
        order by case
        when UPPER(currencyName) == UPPER(:query) then 1
        when UPPER(currencyName) like UPPER(:escaped) then 2
        end
        """
    }
}
