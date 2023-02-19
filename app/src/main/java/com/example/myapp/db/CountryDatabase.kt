package com.example.myapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.db.dao.DaoSummary
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.model.db.SummaryEntity

// основной класс по работе с базой данных
@Database(
    entities = [CountryEntity::class, SummaryEntity::class],
    version = 28,
    exportSchema = false
)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun getCountryDao(): DaoCountry
    abstract fun getSummaryDao(): DaoSummary
}