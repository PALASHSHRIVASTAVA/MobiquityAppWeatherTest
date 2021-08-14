package com.palash.mobiquityappweathertest.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.palash.mobiquityappweathertest.model.CurrentDayResponse
import com.palash.mobiquityappweathertest.network.CITY_LIST
import com.palash.mobiquityappweathertest.network.NetworkErrorListner
import com.palash.mobiquityappweathertest.network.UNITS
import com.palash.mobiquityappweathertest.repository.NetworkRepository
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.Type
import java.util.*

class MapViewViewModel(val networkRepository: NetworkRepository,sharedPrefrenceDB: SharedPrefrenceDB) : BaseViewModel(sharedPrefrenceDB) {

    val currentDayMutableLiveData =  MutableLiveData<CurrentDayResponse?>()


    override fun onCleared() {
        super.onCleared()

    }

    fun getCityTemprature(map:Map<String,Any>,newtworkErrorListner: NetworkErrorListner
    )
    {
        disposables.add(networkRepository.getCurrentDayWeatherReport(currentDayMutableLiveData,map,newtworkErrorListner))
    }




  override  fun removeObserver(lifecycleOwner: LifecycleOwner)
    {

        super.removeObserver(lifecycleOwner)
        currentDayMutableLiveData.value = null
        currentDayMutableLiveData.removeObservers(lifecycleOwner)
        currentDayMutableLiveData.removeObserver {  }


    }






}