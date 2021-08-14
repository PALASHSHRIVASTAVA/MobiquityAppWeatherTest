package com.palash.mobiquityappweathertest.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.palash.mobiquityappweathertest.repository.NetworkRepository
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB

class MainViewModel(val networkRepository: NetworkRepository,sharedPrefrenceDB: SharedPrefrenceDB): BaseViewModel(sharedPrefrenceDB)
{

        val isDialogShow = networkRepository.isDialogShow



    fun logoutFun()
    {
        networkRepository.sharedPreferences.clearShredPrefrences()
    }


    override fun onCleared() {
        super.onCleared()

    }



    override fun removeObserver(lifecycleOwner: LifecycleOwner)
    {
        super.removeObserver(lifecycleOwner)

    }
}