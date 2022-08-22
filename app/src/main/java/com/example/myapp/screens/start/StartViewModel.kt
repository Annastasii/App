package com.example.myapp.screens.start


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.country.MapToDB
import com.example.myapp.model.db.CountryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val screenStart: State<List<CountryDB>> get() = _screenStart
    private val _screenStart = mutableStateOf(listOf<CountryDB>())
    val screenStartFilter: State<List<CountryDB>> get() = _screenStartFilter
    private val _screenStartFilter = mutableStateOf(listOf<CountryDB>())

    init {
        setCountry()
    }

    // перенос данных по элементу
    private fun setCountry() {
        viewModelScope.launch {
            repository.getCountryApi().let { list ->
                repository.insertCountry(list.map { MapToDB.mapper(it) })
            }
            getCountry()
        }
    }

    // получить список (room)
    fun getCountry() {
        viewModelScope.launch {
            val result = repository.getCountry()
            _screenStart.value = result
        }
    }

    // filter (text edit)
    fun filter(text: String) {
        viewModelScope.launch {
            delay(1000)
            _screenStartFilter.value = repository.getFilteredCountry(text)
        }
    }
}
