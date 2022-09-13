package com.example.myapp.screens.usecase

import android.content.Context
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.util.mapToCountryEntity
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(context: Context): List<CountryEntity> {

        return if (repository.hasConnection(context)) {
            repository.getCountryApi().let { list ->
                repository.insertCountry(list.map { it.mapToCountryEntity() })
            }
            repository.getCountry()
        } else {
            repository.getCountry()

        }
    }
}

