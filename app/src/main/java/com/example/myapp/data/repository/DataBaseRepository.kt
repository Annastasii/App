package com.example.myapp.data.repository

import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.model.db.ConfirmedBD
import com.example.myapp.model.db.CountryDB
import javax.inject.Inject

class DataBaseRepository @Inject constructor(
    private val daoConfirmed: DaoConfirmed,
    private val daoCountry: DaoCountry
) {
    // добавление данных в базу данных
    suspend fun insertCountry(list: List<CountryDB>) {
        daoCountry.insert(list)
    }

    suspend fun getCountry(): List<CountryDB> {
        return daoCountry.getCountry()
    }

    suspend fun getFilteredCountry(text: String): List<CountryDB> {
        return daoCountry.getFilteredCountry(text)
    }

    suspend fun insertConfirmed(list: List<ConfirmedBD>) {
        daoConfirmed.insertConfirmed(list)
    }

    suspend fun getConfirmed(country: String): List<ConfirmedBD> {
        return daoConfirmed.getConfirmed(country)
    }
}