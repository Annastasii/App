package com.example.myapp

import android.app.Application
import androidx.room.Room
import com.example.myapp.db.CountryDatabase
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.db.dao.DaoCountry
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    private var database: CountryDatabase? = null
    private var countryDao: DaoCountry? = null
    private var confirmedDao: DaoConfirmed? = null
//    lateinit var appComponent: AppComponent

    companion object {
        var instance: App? = null

        @JvmName("getInstance1")
        fun getInstance(): App? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

//        appComponent = DaggerAppComponent.create()

        instance = this

        //  инициализация базы данных
        database = Room.databaseBuilder(applicationContext, CountryDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()

        //  получить dao
        countryDao = database!!.getCountryDao()
        confirmedDao = database!!.getConfirmedDao()

    }

    //    получение базы даных
    @JvmName("getDatabase1")
    fun getDatabase(): CountryDatabase? {
        return database
    }
}
