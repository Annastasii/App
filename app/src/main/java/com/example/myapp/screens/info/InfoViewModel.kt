package com.example.myapp.screens.info

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.db.SummaryEntity
import com.example.myapp.util.mapToSummaryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val summary: State<List<SummaryEntity>> get() = _summary
    private val _summary = mutableStateOf(listOf<SummaryEntity>())
    val isConnection: MutableState<Boolean> get() = _isConnection
    private val _isConnection = mutableStateOf(true)


    // перенос данных из API в SummaryEntity
    fun setSummary(country: String) {
        viewModelScope.launch {
            repository.getSummaryApi().let { list ->
                repository.insertSummary(list.map { it.mapToSummaryEntity() })
            }
            getSummary(country)
        }
    }

    //    получить лист Countries
    fun getSummary(country: String) {
        viewModelScope.launch {
            _summary.value = listOf(repository.getSummary(country))
        }
    }

    // проверка подключения к интернету
    fun isConnection(context: Context) {
        viewModelScope.launch {
            _isConnection.value = repository.hasConnection(context)
        }
    }

}
