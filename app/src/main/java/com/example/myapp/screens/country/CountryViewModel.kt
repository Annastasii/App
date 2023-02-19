package com.example.myapp.screens.country

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.screens.usecase.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryUseCase: GetCountryUseCase
) : ViewModel() {

    val countryItem: State<List<CountryEntity>> get() = _countryItem
    private val _countryItem = mutableStateOf(listOf<CountryEntity>())

    fun getCountry(context: Context) {
        viewModelScope.launch {
            _countryItem.value = countryUseCase(context)
        }
    }
}
