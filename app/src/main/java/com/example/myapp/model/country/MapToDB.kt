package com.example.myapp.model.country

import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.model.db.CountryDB
import javax.inject.Inject

class MapToDB {
    companion object{
        // перенос данных из CountryItem в CountryDB
        fun mapper(list: CountryItem) = CountryDB(list.country, list.iso2, list.slug)
    }
}
