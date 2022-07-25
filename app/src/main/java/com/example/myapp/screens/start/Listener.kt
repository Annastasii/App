package com.example.myapp.screens.start

import com.example.myapp.model.db.CountryDB

interface Listener {
    fun onClick(country: CountryDB)
}