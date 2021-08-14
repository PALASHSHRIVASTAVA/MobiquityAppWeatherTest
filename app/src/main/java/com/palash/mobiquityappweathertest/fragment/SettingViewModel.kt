package com.palash.mobiquityappweathertest.fragment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.palash.mobiquityappweathertest.network.UNITS
import com.palash.mobiquityappweathertest.repository.NetworkRepository
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB
import com.palash.mobiquityappweathertest.viewmodel.BaseViewModel

class SettingViewModel  (val networkRepository: NetworkRepository, sharedPrefrenceDB: SharedPrefrenceDB) : BaseViewModel(sharedPrefrenceDB) {

    // TODO: Implement the ViewModel

    fun setUnitValues(type:String)
    {
        sharedPrefrenceDB.initStringVal(UNITS,type)
    }

    fun resetAllBookMarked()
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