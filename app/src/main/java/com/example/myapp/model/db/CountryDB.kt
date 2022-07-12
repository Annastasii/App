package com.example.myapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//описание сущностей таблицы
@Entity(tableName = "country_table")
data class CountryDB(

    @PrimaryKey
    val country: String,

    @ColumnInfo(name = "iso2")
    val iso2: String,

    @ColumnInfo(name = "slug")
    val slug: String,
) : Serializable