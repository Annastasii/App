package com.example.myapp.screens.start


import android.app.Application
import androidx.lifecycle.*
import com.example.myapp.REPOSITORY
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.CountryDatabase
import com.example.myapp.db.repository.Realizate
import com.example.myapp.model.country.CountryDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.myapp.model.country.CountryItem as CountryItem


class StartViewModel(application: Application): AndroidViewModel(application) {
    val rep = Repository()
//    val countryList: MutableLiveData<Response<Country>> = MutableLiveData()
    var list = listOf<CountryItem>()
    val context = application


//    инициализация базы данных
    fun initDB(){
        val dao = CountryDatabase.getInstance(context).getCountryDao()
        REPOSITORY = Realizate(dao)
    }

//    перенос данных из countryitem в countrybd
    fun mapper(list: CountryItem) = CountryDB( list.country, list.iso2, list.slug)

//    добавление данных в базу данных
    fun insert(model: CountryDB, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(model){
                onSuccess()
            }
        }
    }

//    перенос данных по элементу
    fun setCountry(){
        viewModelScope.launch {
            val response = rep.getCountr()

            response.body().let { item->
                item?.forEach {
                    insert(mapper(it)){}
                }
            }
        }
    }
//  получить список (room)
    fun getCountry(): LiveData<List<CountryDB>> {
        return REPOSITORY.allCountry
    }

//    filter
    fun filter(text: String): LiveData<List<CountryDB>> {
        return REPOSITORY.filtered(text)
    }
}

