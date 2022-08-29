package com.example.myapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.model.db.SummaryEntity

/**описание методов для работы с базой данных*/
@Dao
interface DaoSummary {

    //    передача данных
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfirmed(model: List<SummaryEntity>)

    //    получение данных
    @Query("SELECT * from confirmed_table WHERE country =:country")
    suspend fun getConfirmed(country: String): SummaryEntity
}