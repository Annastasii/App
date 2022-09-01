package com.example.myapp.screens.usecase

import android.content.Context
import android.widget.Toast
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.db.SummaryEntity
import com.example.myapp.util.mapToSummaryEntity
import javax.inject.Inject

interface GetInfoUseCase {
    suspend operator fun invoke(context: Context, country: String): SummaryEntity
}

class GetInfoUseCaseImp @Inject constructor(
    private val repository: Repository
) : GetInfoUseCase {
    override suspend fun invoke(context: Context, country: String): SummaryEntity {

        return if (repository.hasConnection(context)) {
            repository.getSummaryApi().let { list ->
                repository.insertSummary(list.map { it.mapToSummaryEntity() })
            }
            repository.getSummary(country)
        } else {
            Toast.makeText(context, "No server", Toast.LENGTH_LONG).show()
            repository.getSummary(country)
        }
    }
}