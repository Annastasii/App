package com.example.myapp.model.confirm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "confirmed_table")
class ConfirmedBD (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val slug: String = "",

    @ColumnInfo
    val totalConfirmed: Int = 0,

    @ColumnInfo
    val totalDeaths: Int = 0,

    @ColumnInfo
    val totalRecovered: Int = 0,

    @ColumnInfo
    val confirmed: Int = 0,

    @ColumnInfo
    val country: String = "",

    @ColumnInfo
    val countryCode: String = "",

    @ColumnInfo
    val date: String = "",

    @ColumnInfo
    val deaths: Int = 0,

    @ColumnInfo
    val recovered: Int = 0
    ) {
    }