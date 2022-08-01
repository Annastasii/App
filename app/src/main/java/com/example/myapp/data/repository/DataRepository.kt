package com.example.myapp.data.repository

import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.db.dao.DaoSummary
import com.example.myapp.model.db.CountryDB
import com.example.myapp.model.db.SummaryDB
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val daoConfirmed: DaoSummary,
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

    suspend fun insertConfirmed(list: List<SummaryDB>) {
        daoConfirmed.insertConfirmed(list)
    }

    suspend fun getConfirmed(country: String): List<SummaryDB> {
        return daoConfirmed.getConfirmed(country)
    }
}