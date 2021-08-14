package com.palash.mobiquityappweathertest.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.palash.mobiquityappweathertest.model.CurrentDayResponse
import com.palash.mobiquityappweathertest.model.FiveDaysWatherResponse
import com.palash.mobiquityappweathertest.network.NetworkErrorListner
import com.palash.mobiquityappweathertest.repository.NetworkRepository
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB

class CityTempratureDetailsViewModel (val networkRepository: NetworkRepository, sharedPrefrenceDB: SharedPrefrenceDB) : BaseViewModel(sharedPrefrenceDB) {

    val fiveDayMutableLiveData =  MutableLiveData<FiveDaysWatherResponse?>()
    val currentDayMutableLiveData =  MutableLiveData<CurrentDayResponse?>()


    override fun onCleared() {
        super.onCleared()

    }

    fun getCityTempratureDetails(map:Map<String,Any>,newtworkErrorListner: NetworkErrorListner
    )
    {
        disposables.add(networkRepository.getFiveDaysWatherReport(fiveDayMutableLiveData,map,newtworkErrorListner))
    }


    fun getCityTemprature(map:Map<String,Any>,newtworkErrorListner: NetworkErrorListner
    )
    {
        disposables.add(networkRepository.getCurrentDayWeatherReport(currentDayMutableLiveData,map,newtworkErrorListner))
    }




    override  fun removeObserver(lifecycleOwner: LifecycleOwner)
    {

        super.removeObserver(lifecycleOwner)
        fiveDayMutableLiveData.value = null
        fiveDayMutableLiveData.removeObservers(lifecycleOwner)
        fiveDayMutableLiveData.removeObserver {  }

        currentDayMutableLiveData.value = null
        currentDayMutableLiveData.removeObservers(lifecycleOwner)
        currentDayMutableLiveData.removeObserver {  }


    }






}