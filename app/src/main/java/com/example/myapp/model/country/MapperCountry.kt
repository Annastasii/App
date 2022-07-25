package com.example.myapp.model.country

import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.model.db.CountryDB
import javax.inject.Inject

class MapperCountry @Inject constructor(
    private val daoCountry: DaoCountry,
) {
    // перенос данных из CountryItem в CountryDB
    private fun mapper(list: CountryItem) = CountryDB(list.country, list.iso2, list.slug)

    // добавление данных в базу данных
    private suspend fun insertCountry(model: CountryDB) {
        daoCountry.insert(model)
    }

    suspend fun map(model: CountryItem) {
        insertCountry(mapper(model))
    }
}
