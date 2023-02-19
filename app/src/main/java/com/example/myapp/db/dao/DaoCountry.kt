package com.example.myapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.model.db.CountryEntity


/**описание методов для работы с базой данных*/
@Dao
interface DaoCountry {

    //    передача данных
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CountryEntity>)

    //    получение данных
    @Query("SELECT * from country_table")
    suspend fun getCountry(): List<CountryEntity>
}