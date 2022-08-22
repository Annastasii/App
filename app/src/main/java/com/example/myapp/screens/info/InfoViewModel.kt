package com.example.myapp.screens.info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.confirm.MapToDBSummary
import com.example.myapp.model.db.SummaryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val screenInfo: State<List<SummaryDB>> get() = _screenInfo
    private val _screenInfo = mutableStateOf(listOf<SummaryDB>())

    init {

    }

    // перенос данных по элементу
    fun setConfirmed(country: String) {
        viewModelScope.launch {
            repository.getSummaryApi().let { list ->
                repository.insertSummary(list.map { MapToDBSummary.mapper(it) })
            }
            getConfirmed(country)
        }
    }

    // получить список (room)
    private fun getConfirmed(country: String) {
        viewModelScope.launch {
            val result = repository.getSummary(country)
            _screenInfo.value = result
        }
    }
}
