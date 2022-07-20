//package com.example.myapp.data.api
//
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
///**инициализация retrofit*/
//object RetrofitInstance {
//
//    private val logging = HttpLoggingInterceptor()
//
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://api.covid19api.com/")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val api: ApiService by lazy {
//        retrofit.create(ApiService::class.java)
//    }
//}