package com.example.myapp.data.repository

import com.example.myapp.data.api.ApiService
import com.example.myapp.model.confirm.Confirmed
import com.example.myapp.model.country.CountryItem
import javax.inject.Inject


/**реализация запросов retrofit*/
class Repository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getCountry(): List<CountryItem>? {
        return api.getCountry().body()
    }

    suspend fun getSummary(): List<Confirmed> {
        return api.getSummary().body()!!.countries
    }
}