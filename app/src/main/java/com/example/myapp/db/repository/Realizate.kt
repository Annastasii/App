package com.example.myapp.db.repository

import androidx.lifecycle.LiveData
import com.example.myapp.db.dao.Dao
import com.example.myapp.model.country.CountryDB

class Realizate(private val dao: Dao): RepositoryBD {
    override val allCountry: LiveData<List<CountryDB>>
        get() = dao.getAll()

    override suspend fun insert(model: CountryDB, onSuccess: () -> Unit) {
        dao.insert(model)
        onSuccess()
    }

    override fun filtered(country: String): LiveData<List<CountryDB>> {
        return dao.filtered(country)
    }
}