package com.example.myapp.data.api

import com.example.myapp.model.confirm.All
import com.example.myapp.model.confirm.Confirmed
import com.example.myapp.model.country.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("./countries")
    suspend fun getCountry(): Response<Country>
    @GET("/summary")
    suspend fun getSummary(): Response<All>
}