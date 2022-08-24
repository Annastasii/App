package com.example.myapp.screens.start


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.util.mapperToEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val countryItem: State<List<CountryEntity>> get() = _countryItem
    private val _countryItem = mutableStateOf(listOf<CountryEntity>())

    init {
        setCountry()
    }

    // перенос данных по элементу
    private fun setCountry() {
        viewModelScope.launch {
            repository.getCountryApi().let { list ->
                repository.insertCountry(list.map { it.mapperToEntity() })
            }
            getCountry()
        }
    }

    // получить список стран (room)
    private fun getCountry() {
        viewModelScope.launch {
            _countryItem.value = repository.getCountry()
        }
    }
}
