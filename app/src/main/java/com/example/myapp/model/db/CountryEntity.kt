package com.example.myapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/** описание сущностей таблицы country_table */
@Entity(tableName = "country_table")
data class CountryEntity(

    @PrimaryKey
    val country: String,

    @ColumnInfo(name = "iso2")
    val iso2: String,

    @ColumnInfo(name = "slug")
    val slug: String,
) : Serializable