package com.example.myapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.App
import com.example.myapp.db.CountryDatabase
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.db.dao.DaoCountry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {


//    @Provides
//    @Singleton
//    fun provideGetInstance(): App? {
//        return App.getInstance()
//    }

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

    @Provides
    @Singleton
    fun provideGetDaoCountry(countryDatabase: CountryDatabase): DaoCountry {
        return countryDatabase.getCountryDao()
    }

    @Provides
    @Singleton
    fun provideGetDaoConfirmed(countryDatabase: CountryDatabase): DaoConfirmed {
        return countryDatabase.getConfirmedDao()
    }

}