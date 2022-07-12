package com.example.myapp.screens.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.App
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.model.confirm.Confirmed
import com.example.myapp.model.db.ConfirmedBD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InfoViewModel : ViewModel() {
    private val repo = Repository()
    private val livedata: MutableLiveData<List<ConfirmedBD>> = MutableLiveData()
    private var confirmedDao: DaoConfirmed? = null

    // получить базу данных
    fun getDB() {
        val database = App.getInstance()?.getDatabase()
        confirmedDao = database?.getConfirmedDao()
    }

    // mapper Confirmed to ConfirmedBD
    private fun mapperInfo(list: Confirmed) = ConfirmedBD(
        list.slug,
        list.totalConfirmed,
        list.totalDeaths,
        list.totalRecovered,
        list.confirmed,
        list.country,
        list.countryCode,
        list.date,
        list.deaths,
        list.recovered
    )

    // добавление даных в базу данных
    private fun insertConfirmed(model: ConfirmedBD) {
        viewModelScope.launch(Dispatchers.IO) {
            confirmedDao?.insert(model)
        }
    }

    // перенос данных по элементу
    fun setConfirmed() {
        viewModelScope.launch {
            val response = repo.getSummary()
            response.body()?.let { summary ->
                summary.countries.let { item ->
                    item.forEach {
                        insertConfirmed(mapperInfo(it))
                    }
                }
            }
        }
    }

    // получить список (room)
    fun getConfirmed(country: String): LiveData<List<ConfirmedBD>> {
        viewModelScope.launch {
            livedata.postValue(confirmedDao?.getAll(country))
        }
        return livedata
    }
}
