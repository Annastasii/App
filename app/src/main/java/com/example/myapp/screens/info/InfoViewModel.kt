package com.example.myapp.screens.info

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.model.db.SummaryEntity
import com.example.myapp.screens.usecase.GetInfoUseCaseImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val infoUseCaseImp: GetInfoUseCaseImp
) : ViewModel() {

    val summary: State<List<SummaryEntity>> get() = _summary
    private val _summary = mutableStateOf(listOf<SummaryEntity>())


    fun getCountry(context: Context, country: String) {
        viewModelScope.launch {

            _summary.value = listOf(infoUseCaseImp(context, country))
        }
    }

}
