package com.example.myapp.util

import com.example.myapp.model.confirm.Countries
import com.example.myapp.model.country.CountryItem
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.model.db.SummaryEntity

fun CountryItem.mapperToEntity(): CountryEntity = CountryEntity(country, iso2, slug)

fun Countries.mapperToEntity(): SummaryEntity = SummaryEntity(
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