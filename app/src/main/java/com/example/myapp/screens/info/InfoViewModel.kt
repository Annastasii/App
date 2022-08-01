package com.example.myapp.screens.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.repository.DataRepository
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.confirm.MapToDBSummary
import com.example.myapp.model.db.SummaryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: Repository,
    private val dataRepository: DataRepository
) : ViewModel() {


    private val livedata: MutableLiveData<List<SummaryDB>> = MutableLiveData()

    // перенос данных по элементу
    fun setConfirmed() {
        viewModelScope.launch {
            repository.getSummary().let { list ->
                dataRepository.insertConfirmed(list.map { MapToDBSummary.mapper(it) })
            }
        }
    }

    // получить список (room)
    fun getConfirmed(country: String): LiveData<List<SummaryDB>> {
        viewModelScope.launch {
            livedata.postValue(dataRepository.getConfirmed(country))
        }
        return livedata
    }
}
