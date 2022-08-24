package com.example.myapp.model.confirm

import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("Countries")
    val countries: List<Countries>
)