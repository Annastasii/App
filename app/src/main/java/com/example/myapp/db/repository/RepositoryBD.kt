package com.example.myapp.db.repository

import androidx.lifecycle.LiveData
import com.example.myapp.model.confirm.ConfirmedBD
import com.example.myapp.model.country.CountryDB

interface RepositoryBD {
    val allCountry :LiveData<List<CountryDB>>

    suspend fun insert(model: CountryDB, onSuccess:() -> Unit)

    fun filtered(country: String) : LiveData<List<CountryDB>>
}