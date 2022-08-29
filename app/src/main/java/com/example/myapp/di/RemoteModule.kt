package com.example.myapp.di

import com.example.myapp.data.api.ApiService
import com.example.myapp.data.repository.Repository
import com.example.myapp.db.dao.DaoCountry
import com.example.myapp.db.dao.DaoSummary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** Модуль по работе с сервером */
@Module(includes = [DataBaseModule::class])
@InstallIn(SingletonComponent::class)
class RemoteModule {

    //    подключить HttpLoggingInterceptor
    @Provides
    @Singleton
    fun provideLogin(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    // подключить OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    //    подключить Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    //    создать apiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        api: ApiService,
        daoSummary: DaoSummary,
        daoCountry: DaoCountry
    ): Repository {
        return Repository(api, daoSummary, daoCountry)
    }
}