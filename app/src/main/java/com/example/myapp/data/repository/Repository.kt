package com.example.myapp.data.repository

import com.example.myapp.data.api.ApiService
import com.example.myapp.model.confirm.Summary
import com.example.myapp.model.country.CountryItem
import retrofit2.Response
import javax.inject.Inject


/**реализация запросов retrofit*/
class Repository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getCountry(): Response<List<CountryItem>> {
        return api.getCountry()
    }

    suspend fun getSummary(): Response<Summary> {
        return api.getSummary()
    }
}