package com.example.myapp.screens.start


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    var livedata: MutableLiveData<List<CountryDB>> = MutableLiveData()
    private val livedataFilter: MutableLiveData<List<CountryDB>> = MutableLiveData()

    // перенос данных по элементу
    fun setCountry() {
        viewModelScope.launch {
            repository.getCountryApi().let { list ->
                repository.insertCountry(list!!.map { MapToDB.mapper(it) })
            }
        }
    }

    // получить список (room)
    fun getCountry() {
        viewModelScope.launch {
            livedata.postValue(repository.getCountry())
    }
}

    // filter (text edit)
    fun filter(text: String): LiveData<List<CountryDB>> {
        viewModelScope.launch {
            delay(1000)
            livedataFilter.postValue(repository.getFilteredCountry(text))
        }
        return livedataFilter
    }
}

