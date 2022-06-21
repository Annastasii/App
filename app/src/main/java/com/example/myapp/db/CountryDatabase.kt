package com.example.myapp.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.db.dao.Dao
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.model.confirm.ConfirmedBD
import com.example.myapp.model.country.CountryDB


@Database(entities = [CountryDB::class, ConfirmedBD::class], version = 11)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun getCountryDao(): Dao
    abstract fun getConfirmedDao(): DaoConfirmed

    companion object{
        private var database: CountryDatabase ?= null

        @Synchronized
        fun getInstance(context: Context):CountryDatabase{
            return if (database == null){
                database = Room.databaseBuilder(context, CountryDatabase::class.java, "db")
                    .fallbackToDestructiveMigration()
                    .build()
                database as CountryDatabase
            }else{
                database as CountryDatabase
            }
        }
    }
}