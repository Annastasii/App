package com.example.myapp.data.api

import com.example.myapp.model.confirm.Summary
import com.example.myapp.model.country.CountryItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**запросы retrofit*/
interface ApiService {
    @GET("./countries")
    suspend fun getCountry(): Response<List<CountryItem>>

    @GET("/summary")
    suspend fun getSummary(): Response<Summary>
}