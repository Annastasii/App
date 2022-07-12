//package com.example.myapp.di
//
//import com.example.myapp.data.api.ApiService
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@Module
//class RemoteModule {
//
//    @Provides
//    fun provideLogin(): HttpLoggingInterceptor = HttpLoggingInterceptor()
//
//    @Provides
//    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//    @Provides
//    fun provideRetrofit(client: OkHttpClient): Retrofit =
//        Retrofit.Builder()
//            .baseUrl("https://api.covid19api.com/")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService =
//        retrofit.create(ApiService::class.java)
//}