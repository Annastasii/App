package com.example.myapp.data.api

import com.example.myapp.model.confirm.Summary
import com.example.myapp.model.country.CountryItem
import retrofit2.Response
import retrofit2.http.GET

/** запросы retrofit */
interface ApiService {

    //    запрос списка стран(CountryItem) из API
    @GET("./countries")
    suspend fun getCountry(): Response<List<CountryItem>>

    //    запрос объекта(Summary) из API
    @GET("/summary")
    suspend fun getSummary(): Response<Summary>
}