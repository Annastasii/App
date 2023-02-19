package com.example.myapp.di

import android.content.Context
import androidx.room.Room
import com.example.myapp.db.CountryDatabase
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.db.dao.DaoSummary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Модуль по работе с базой данных */
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    //    получение базы данных
    @Provides
    @Singleton
    fun provideGetDataBase(@ApplicationContext appContext: Context): CountryDatabase {
        return Room.databaseBuilder(
            appContext,
            CountryDatabase::class.java,
            "database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    //    получение DaoCountry
    @Provides
    @Singleton
    fun provideGetDaoCountry(countryDatabase: CountryDatabase): DaoCountry {
        return countryDatabase.getCountryDao()
    }

    //    получение DaoSummary
    @Provides
    @Singleton
    fun provideGetDaoConfirmed(countryDatabase: CountryDatabase): DaoSummary {
        return countryDatabase.getSummaryDao()
    }
}