package com.example.myapp.screens.start


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.App
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.model.country.CountryItem
import com.example.myapp.model.db.CountryDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class StartViewModel : ViewModel() {
    private val rep = Repository()
    var list = listOf<CountryItem>()
    private var countryDao: DaoCountry? = null
    private val livedata: MutableLiveData<List<CountryDB>> = MutableLiveData()
    private val livedataFilter: MutableLiveData<List<CountryDB>> = MutableLiveData()


    // инициализация базы данных
    fun initDB() {
        val db = App.getInstance()?.getDatabase()
        countryDao = db?.getCountryDao()
    }

    // перенос данных из CountryItem в CountryDB
    private fun mapper(list: CountryItem) = CountryDB(list.country, list.iso2, list.slug)

    // добавление данных в базу данных
    private fun insert(model: CountryDB) {
        viewModelScope.launch(Dispatchers.IO) {
            countryDao?.insert(model)
        }
    }

    // перенос данных по элементу
    fun setCountry() {
        viewModelScope.launch {
            val response = rep.getCountry()
            response.body().let { item ->
                item?.forEach {
                    insert(mapper(it))
                }
            }
        }
    }

    // получить список (room)
    fun getCountry(): LiveData<List<CountryDB>> {
        viewModelScope.launch {
            livedata.postValue(countryDao!!.getAll())
        }
        return livedata
    }

    // filter (text edit)
    fun filter(text: String): LiveData<List<CountryDB>> {
        viewModelScope.launch {
            delay(1000)
            livedataFilter.postValue(countryDao?.getFilteredItems(text))
        }
        return livedataFilter
    }
}

