package com.example.myapp.screens.usecase

import android.content.Context
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.db.SummaryEntity
import com.example.myapp.util.mapToSummaryEntity
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(context: Context, country: String): SummaryEntity {

        return if (repository.hasConnection(context)) {
            repository.getSummaryApi().let { list ->
                repository.insertSummary(list.map { it.mapToSummaryEntity() })
            }
            repository.getSummary(country)
        } else {
            repository.getSummary(country)
        }
    }
}