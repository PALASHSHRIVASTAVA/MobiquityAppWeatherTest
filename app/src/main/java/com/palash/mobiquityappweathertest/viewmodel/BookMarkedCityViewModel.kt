package com.palash.mobiquityappweathertest.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.palash.mobiquityappweathertest.repository.NetworkRepository
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB

class BookMarkedCityViewModel (val networkRepository: NetworkRepository,sharedPrefrenceDB: SharedPrefrenceDB) : BaseViewModel(sharedPrefrenceDB) {

    val isCityAvailabe: MutableLiveData<Boolean> = MutableLiveData()


    override fun removeObserver(lifecycleOwner: LifecycleOwner)
    {
        super.removeObserver(lifecycleOwner)
        isCityAvailabe.value = null
        isCityAvailabe.removeObservers(lifecycleOwner)
        isCityAvailabe.removeObserver {  }

    }






}