package com.example.myapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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

    //     получить список стран(CountryItem) из API
    suspend fun getCountryApi(): List<CountryItem> {
        val response = api.getCountry()
        return response.body()!!
    }

    //    получить лист Countries из API
    suspend fun getSummaryApi(): List<Countries> {
        val response = api.getSummary()
        return response.body()!!.countries
    }

    // добавить данные в таблицу country_table
    suspend fun insertCountry(list: List<CountryEntity>) {
        daoCountry.insert(list)
    }

    // получить данные из таблицы country_table
    suspend fun getCountry(): List<CountryEntity> {
        return daoCountry.getCountry()
    }

    // добавить данные в таблицу confirmed_table
    suspend fun insertSummary(list: List<SummaryEntity>) {
        daoSummary.insertConfirmed(list)
    }

    // получить данные из таблицы confirmed_table
    suspend fun getSummary(country: String): SummaryEntity {
        return daoSummary.getConfirmed(country)
    }

    // проверка подключения к интернету
    fun hasConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return !(netInfo == null || !netInfo.isConnected)
    }
}