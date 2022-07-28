package com.example.myapp.screens.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.repository.DataBaseRepository
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.confirm.MapToDBConfirmed
import com.example.myapp.model.db.ConfirmedBD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: Repository,
    private val databaseRepository: DataBaseRepository
) : ViewModel() {


    private val livedata: MutableLiveData<List<ConfirmedBD>> = MutableLiveData()

    // перенос данных по элементу
    fun setConfirmed() {
        viewModelScope.launch {
            repository.getSummary()?.let { list ->
                databaseRepository.insertConfirmed(list.map { MapToDBConfirmed.mapper(it) })
            }
        }
    }

    // получить список (room)
    fun getConfirmed(country: String): LiveData<List<ConfirmedBD>> {
        viewModelScope.launch {
            livedata.postValue(databaseRepository.getConfirmed(country))
        }
        return livedata
    }
}
