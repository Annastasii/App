package com.example.myapp.data.repository

import com.example.myapp.data.api.RetrofitInstance
import com.example.myapp.model.confirm.Summary
import com.example.myapp.model.country.CountryItem
import retrofit2.Response


/**реализация запросов retrofit*/
class Repository {

    suspend fun getCountry(): Response<List<CountryItem>> {
        return RetrofitInstance.api.getCountry()
    }

    suspend fun getSummary(): Response<Summary> {
        return RetrofitInstance.api.getSummary()
    }
}