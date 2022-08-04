package com.example.myapp.data.repository

import android.provider.CallLog
import com.example.myapp.data.api.ApiService
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.db.dao.DaoSummary
import com.example.myapp.model.confirm.Contries
import com.example.myapp.model.country.CountryItem
import com.example.myapp.model.db.CountryDB
import com.example.myapp.model.db.SummaryDB
import org.chromium.base.Log
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log


/**реализация запросов retrofit*/
class Repository @Inject constructor(
    private val api: ApiService,
    private val daoConfirmed: DaoSummary,
    private val daoCountry: DaoCountry
) {

    suspend fun getCountryApi(): List<CountryItem>? {
        val response = api.getCountry()
        try {
            response.isSuccessful
        }catch (e: Exception){
            Log.e("retrofit", "${response.errorBody()}")
        }
        return response.body()
    }

    suspend fun getSummaryApi(): List<Contries> {
        val response = api.getSummary()
        try {
            response.isSuccessful
        }catch (e: Exception){
            Log.e("retrofit", "${response.errorBody()}")
        }
        return response.body()!!.countries
    }

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

    suspend fun insertSummary(list: List<SummaryDB>) {
        daoConfirmed.insertConfirmed(list)
    }

    suspend fun getSummary(country: String): List<SummaryDB> {
        return daoConfirmed.getConfirmed(country)
    }
}