package com.example.myapp.data.repository

import com.example.myapp.data.api.ApiService
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.db.dao.DaoSummary
import com.example.myapp.model.confirm.Countries
import com.example.myapp.model.country.CountryItem
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.model.db.SummaryEntity
import org.chromium.base.Log
import javax.inject.Inject


/**реализация запросов retrofit*/
class Repository @Inject constructor(
    private val api: ApiService,
    private val daoSummary: DaoSummary,
    private val daoCountry: DaoCountry
) {

    suspend fun getCountryApi(): List<CountryItem> {
        val response = api.getCountry()
        response.body()
        try {
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("retrofit", "${response.errorBody()}")
        }
        return response.body()!!
    }

    suspend fun getSummaryApi(): List<Countries> {
        val response = api.getSummary()
        try {
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("retrofit", "${response.errorBody()}")
        }
        return response.body()!!.countries
    }


    // добавление данных в базу данных
    suspend fun insertCountry(list: List<CountryEntity>) {
        daoCountry.insert(list)
    }

    suspend fun getCountry(): List<CountryEntity> {
        return daoCountry.getCountry()
    }

    suspend fun insertSummary(list: List<SummaryEntity>) {
        daoSummary.insertConfirmed(list)
    }

    suspend fun getSummary(country: String): SummaryEntity {
        return daoSummary.getConfirmed(country)
    }
}