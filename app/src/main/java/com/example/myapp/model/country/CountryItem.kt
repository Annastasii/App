package com.example.myapp.model.country

import com.google.gson.annotations.SerializedName


/**структура CountryItem*/
data class CountryItem(

    @SerializedName("Country")
    val country: String,

    @SerializedName("ISO2")
    val iso2: String,

    @SerializedName("Slug")
    val slug: String,
)