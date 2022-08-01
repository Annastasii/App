package com.example.myapp.model.confirm

import com.example.myapp.model.db.ConfirmedBD

class MapToDBConfirmed {
    companion object {
        // mapper Confirmed to ConfirmedBD
        fun mapper(list: Confirmed) = ConfirmedBD(
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
    }
}