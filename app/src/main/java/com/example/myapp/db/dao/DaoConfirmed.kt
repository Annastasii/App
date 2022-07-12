package com.example.myapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.model.db.ConfirmedBD

/**описание методов для работы с базой данных*/
@Dao
interface DaoConfirmed {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ConfirmedBD)

    @Query("SELECT * from confirmed_table WHERE country =:country")
    suspend fun getAll(country: String): List<ConfirmedBD>
}