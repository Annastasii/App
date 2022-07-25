package com.example.myapp.model.confirm

import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.model.db.ConfirmedBD
import javax.inject.Inject

class MapperConfirmed @Inject constructor(
    private val daoConfirmed: DaoConfirmed,
) {
    // mapper Confirmed to ConfirmedBD
    private fun mapper(list: Confirmed) = ConfirmedBD(
        list.slug,
        list.totalConfirmed,
        list.totalDeaths,
        list.totalRecovered,
        list.confirmed,
        list.country,
        list.countryCode,
        list.date,
        list.deaths,
        list.recovered
    )

    // добавление даных в базу данных
    private suspend fun insertConfirmed(model: ConfirmedBD) {
        daoConfirmed.insertConfirmed(model)
    }

    suspend fun map(model: Confirmed) {
        insertConfirmed(mapper(model))
    }
}