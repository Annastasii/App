package com.example.myapp.util

import com.example.myapp.model.confirm.Countries
import com.example.myapp.model.country.CountryItem
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.model.db.SummaryEntity

// mapper из CountryItem в CountryEntity
fun CountryItem.mapToCountryEntity(): CountryEntity = CountryEntity(country, iso2, slug)

// mapper из Countries в SummaryEntity
fun Countries.mapToSummaryEntity(): SummaryEntity = SummaryEntity(
    slug,
    totalConfirmed,
    totalDeaths,
    totalRecovered,
    confirmed,
    country,
    countryCode,
    date,
    deaths,
    recovered
)