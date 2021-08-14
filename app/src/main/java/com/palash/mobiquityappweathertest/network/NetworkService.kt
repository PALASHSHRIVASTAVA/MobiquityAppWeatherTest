package com.palash.mobiquityappweathertest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


private fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun basicOkHttpClient() = OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()


fun createBasicAuthService(path:String): RetrofitApiService {

    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).client(basicOkHttpClient())
        .baseUrl(path)
        .build()
    return retrofit.create(RetrofitApiService::class.java);
}