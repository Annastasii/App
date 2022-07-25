package com.example.myapp.screens.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.api.ApiService
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.dao.DaoConfirmed
import com.example.myapp.model.confirm.MapperConfirmed
import com.example.myapp.model.db.ConfirmedBD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    val api: ApiService,
    private val daoConfirmed: DaoConfirmed,
    private val repository: Repository
) : ViewModel() {


    private val livedata: MutableLiveData<List<ConfirmedBD>> = MutableLiveData()

    // перенос данных по элементу
    fun setConfirmed() {
        viewModelScope.launch {
            val response = repository.getSummary()
            response.body().let { summary ->
                summary?.countries.let { item ->
                    item?.forEach {
                        MapperConfirmed(daoConfirmed).map(it)
                    }
                }
            }
        }
    }

    // получить список (room)
    fun getConfirmed(country: String): LiveData<List<ConfirmedBD>> {
        viewModelScope.launch {
            livedata.postValue(daoConfirmed.getConfirmed(country))
        }
        return livedata
    }
}
