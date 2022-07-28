package com.example.myapp

import android.app.Application
import androidx.room.Room
import com.example.myapp.db.CountryDatabase
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.db.dao.DaoCountry
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
}
