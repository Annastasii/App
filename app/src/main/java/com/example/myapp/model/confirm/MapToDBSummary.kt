package com.example.myapp.model.confirm

import com.example.myapp.model.db.SummaryDB

class MapToDBSummary {
    companion object {
        // mapper Confirmed to ConfirmedBD
        fun mapper(item: Contries) = SummaryDB(
            item.slug,
            item.totalConfirmed,
            item.totalDeaths,
            item.totalRecovered,
            item.confirmed,
            item.country,
            item.countryCode,
            item.date,
            item.deaths,
            item.recovered
        )
    }
}