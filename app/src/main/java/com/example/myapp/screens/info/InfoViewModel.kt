package com.example.myapp.screens.info

import android.app.Application
import androidx.lifecycle.*
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.CountryDatabase
import com.example.myapp.db.repository.RealizateConfirmed
import com.example.myapp.model.confirm.Confirmed
import com.example.myapp.model.confirm.ConfirmedBD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.myapp.db.repository.RepositoryConfirmed as RepositoryConfirmed


class InfoViewModel(application: Application):  AndroidViewModel(application) {
    val repo = Repository()
    val context = application
    lateinit var Repository: RepositoryConfirmed

    //    инициализация базы данных
    fun initDB(){
        val dao = CountryDatabase.getInstance(context).getConfirmedDao()
        Repository = RealizateConfirmed(dao)
    }

    //    mapper
    fun mapperInfo(list: Confirmed) = ConfirmedBD(id = 0, list.slug, list.totalConfirmed, list.totalDeaths, list.totalRecovered, list.confirmed, list.country, list.countryCode, list.date, list.deaths, list.recovered)

    //    добавление даных в базу данных
    fun insertConfirmed(model: ConfirmedBD, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            Repository.insertConfirmed(model){
                onSuccess()
            }
        }
    }

    //    перенос данных по элементу
    fun setConfirmed(){
        viewModelScope.launch {
            val response = repo.getSummar()
            response.body()?.let { item ->
                item.Countries.let { list ->
                    list.forEach(){
                        insertConfirmed(mapperInfo(it)){}
                    }

                }
            }
        }
    }

    //  получить список (room)
    fun getConfirmed(country: String): LiveData<List<ConfirmedBD>> {
        return Repository.allConfirmed(country)
    }


}
