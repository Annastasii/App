package com.example.myapp.db.repository

import androidx.lifecycle.LiveData
import com.example.myapp.model.confirm.ConfirmedBD
import com.example.myapp.model.country.CountryDB

interface RepositoryConfirmed {
    fun allConfirmed(country: String) : LiveData<List<ConfirmedBD>>
    suspend fun insertConfirmed(model: ConfirmedBD, onSuccess:() -> Unit)
}