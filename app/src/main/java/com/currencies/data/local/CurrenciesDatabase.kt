package com.currencies.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.currencies.data.local.dao.AllCurrenciesDao
import com.currencies.data.local.dao.MyCurrenciesDao
import com.currencies.data.local.entity.AllCurrencyDB
import com.currencies.data.local.entity.MyCurrencyDB

@Database(entities = [MyCurrencyDB::class, AllCurrencyDB::class], version = 1, exportSchema = false)
abstract class CurrenciesDatabase : RoomDatabase() {

    abstract fun myCurrenciesDao(): MyCurrenciesDao
    abstract fun allCurrenciesDao(): AllCurrenciesDao

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(
                context,
                CurrenciesDatabase::class.java,
                "currencies_database"
            ).build()
    }
}
