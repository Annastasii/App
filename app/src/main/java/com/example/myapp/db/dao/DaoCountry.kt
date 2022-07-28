package com.example.myapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.model.db.CountryDB


/**описание методов для работы с базой данных*/
@Dao
interface DaoCountry {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CountryDB>)

    @Query("SELECT * from country_table")
    suspend fun getCountry(): List<CountryDB>

    @Query("SELECT * from country_table WHERE country LIKE :country ")
    suspend fun getFilteredCountry(country: String): List<CountryDB>
}