package com.example.myapp.db.repository

import androidx.lifecycle.LiveData
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.model.confirm.ConfirmedBD
import com.example.myapp.model.country.CountryDB

class RealizateConfirmed(private val dao: DaoConfirmed): RepositoryConfirmed {
    override fun allConfirmed(country: String): LiveData<List<ConfirmedBD>> {
        return dao.getAll(country)
    }

    override suspend fun insertConfirmed(model: ConfirmedBD, onSuccess: () -> Unit) {
        dao.insert(model)
        onSuccess()
    }
}