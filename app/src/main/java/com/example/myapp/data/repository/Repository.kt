package com.example.myapp.data.repository

import com.example.myapp.data.api.RetrofitInstance
import com.example.myapp.model.confirm.All
import com.example.myapp.model.confirm.Confirmed
import com.example.myapp.model.country.Country
import retrofit2.Response

class Repository() {
    suspend fun getCountr() : Response<Country>{
        return RetrofitInstance.api.getCountry()
    }
    suspend fun getSummar() : Response<All>{
        return RetrofitInstance.api.getSummary()
    }
}