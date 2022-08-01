package com.example.myapp.model.country

import com.example.myapp.model.db.CountryDB

class MapToDB {
    companion object {
        // перенос данных из CountryItem в CountryDB
        fun mapper(item: CountryItem) = CountryDB(item.country, item.iso2, item.slug)
    }
}
