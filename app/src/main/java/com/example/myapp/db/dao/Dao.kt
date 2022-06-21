package com.example.myapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.model.country.CountryDB

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: CountryDB)

    @Query("SELECT * from country_table")
    fun getAll(): LiveData<List<CountryDB>>

    @Query("SELECT * from country_table WHERE country LIKE :country ")
    fun filtered(country: String): LiveData<List<CountryDB>>
}