package com.example.myapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.model.confirm.ConfirmedBD
import com.example.myapp.model.country.CountryDB

@Dao
interface DaoConfirmed {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ConfirmedBD)

    @Query("SELECT * from confirmed_table WHERE country =:country")
    fun getAll(country: String): LiveData<List<ConfirmedBD>>
}