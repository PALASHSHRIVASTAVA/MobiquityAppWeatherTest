package com.palash.mobiquityappweathertest

import android.app.Application
import org.koin.android.ext.android.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule,sharedPrefrenceDB,dialogShow,networkRepository ,viewModelModule))


    }

}