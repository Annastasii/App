package com.example.myapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** описание сущностей таблицы confirmed_table */
@Entity(tableName = "confirmed_table")
data class SummaryEntity(

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "totalConfirmed")
    val totalConfirmed: Int,

    @ColumnInfo(name = "totalDeaths")
    val totalDeaths: Int,

    @ColumnInfo(name = "totalRecovered")
    val totalRecovered: Int,

    @ColumnInfo(name = "confirmed")
    val confirmed: Int,

    @PrimaryKey
    val country: String,

    @ColumnInfo(name = "countryCode")
    val countryCode: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "deaths")
    val deaths: Int,

    @ColumnInfo(name = "recovered")
    val recovered: Int,
)