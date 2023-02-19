package com.example.myapp.model.confirm

import com.google.gson.annotations.SerializedName


/**структура Countries*/
data class Countries(

    @SerializedName("Country")
    val country: String,

    @SerializedName("CountryCode")
    val countryCode: String,

    @SerializedName("Date")
    val date: String,

    @SerializedName("NewConfirmed")
    val confirmed: Int,

    @SerializedName("NewDeaths")
    val deaths: Int,

    @SerializedName("NewRecovered")
    val recovered: Int,

    @SerializedName("Slug")
    val slug: String,

    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,

    @SerializedName("TotalDeaths")
    val totalDeaths: Int,

    @SerializedName("TotalRecovered")
    val totalRecovered: Int,
)