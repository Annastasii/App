package com.example.myapp.screens.start


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.api.ApiService
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.model.country.MapperCountry
import com.example.myapp.model.db.CountryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    val api: ApiService,
    private val daoCountry: DaoCountry,
    private val repository: Repository
) : ViewModel() {

    private val livedata: MutableLiveData<List<CountryDB>> = MutableLiveData()
    private val livedataFilter: MutableLiveData<List<CountryDB>> = MutableLiveData()

    // перенос данных по элементу
    fun setCountry() {
        viewModelScope.launch {
            val response = repository.getCountry()
            response.body().let { item ->
                item?.forEach {
                    MapperCountry(daoCountry).map(it)
                }
            }
        }
    }

    // получить список (room)
    fun getCountry(): LiveData<List<CountryDB>> {
        viewModelScope.launch {
            livedata.postValue(daoCountry.getCountry())
        }
        return livedata
    }

    // filter (text edit)
    fun filter(text: String): LiveData<List<CountryDB>> {
        viewModelScope.launch {
            delay(1000)
            livedataFilter.postValue(daoCountry.getFilteredCountry(text))
        }
        return livedataFilter
    }
}

