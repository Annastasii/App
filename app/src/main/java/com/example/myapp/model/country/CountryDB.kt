package com.example.myapp.model.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "country_table")
class CountryDB (

    @PrimaryKey
    val country: String = "",

    @ColumnInfo
    val iso2: String = "",

    @ColumnInfo
    val slug: String = ""
    ) {
}