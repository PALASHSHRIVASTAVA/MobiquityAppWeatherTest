package com.palash.mobiquityappweathertest.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.palash.mobiquityappweathertest.network.CITY_LIST
import com.palash.mobiquityappweathertest.network.UNITS
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.Type
import java.util.ArrayList

open class BaseViewModel(val sharedPrefrenceDB: SharedPrefrenceDB) : ViewModel() {

    val cityListMutableLiveData =  MutableLiveData<ArrayList<String>?>()
    val cityList: ArrayList<String> = ArrayList<String>()
    val disposables = CompositeDisposable()

    init {
        getCityValue()
    }

    override fun onCleared() {
        super.onCleared()
        if(!disposables.isDisposed)
        {
            disposables.dispose()
        }
    }

    open fun removeObserver(lifecycleOwner: LifecycleOwner)
    {
        cityListMutableLiveData.value = null
        cityListMutableLiveData.removeObservers(lifecycleOwner)
        cityListMutableLiveData.removeObserver {  }

    }

    fun getUnitValue():String?
    {
        return sharedPrefrenceDB.getStringVal(UNITS)
    }

    fun getCityValue()
    {
        val cityVal = sharedPrefrenceDB.getStringVal(CITY_LIST)
        if(cityVal!=null && !cityVal.isEmpty())
        {
            val collectionType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
            val list: ArrayList<String> = Gson().fromJson(cityVal, collectionType)
            cityListMutableLiveData.postValue(list)
        }
        else{
            cityListMutableLiveData.postValue(ArrayList<String>())
        }

    }

    fun saveCity(cityList:String)
    {
        sharedPrefrenceDB.initStringVal(CITY_LIST,cityList)
    }


}