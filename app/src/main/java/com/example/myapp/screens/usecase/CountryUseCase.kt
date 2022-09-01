package com.example.myapp.screens.country.usecase

import android.content.Context
import android.widget.Toast
import com.example.myapp.data.repository.Repository
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.util.mapToCountryEntity
import javax.inject.Inject

interface GetCountryUseCase {
    suspend operator fun invoke(context: Context): List<CountryEntity>
}

class GetCountryUseCaseImp @Inject constructor(
    private val repository: Repository
) : GetCountryUseCase {
    override suspend fun invoke(context: Context): List<CountryEntity> {

        return if (repository.hasConnection(context)) {
            repository.getCountryApi().let { list ->
                repository.insertCountry(list.map { it.mapToCountryEntity() })
            }
            repository.getCountry()
        } else {
            Toast.makeText(context, "No server", Toast.LENGTH_LONG).show()
            repository.getCountry()

        }
    }
}

sealed class Country() {
    data class Connected(val countryEntity: List<CountryEntity>) : Country()
    data class NotConnected(val countryEntity: List<CountryEntity>) : Country()
}