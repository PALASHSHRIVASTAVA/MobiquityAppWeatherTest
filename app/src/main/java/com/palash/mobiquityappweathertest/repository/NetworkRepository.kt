package com.palash.mobiquityappweathertest.repository
import androidx.lifecycle.MutableLiveData
import com.palash.mobiquityappweathertest.network.NetworkErrorListner
import com.palash.mobiquityappweathertest.network.RetrofitApiService
import com.palash.mobiquityappweathertest.network.RxSingleSchedulers
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB
import com.palash.mobiquityappweathertest.model.CurrentDayResponse
import com.palash.mobiquityappweathertest.model.FiveDaysWatherResponse
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class NetworkRepository(val apiService: RetrofitApiService, val schedulers: RxSingleSchedulers, val sharedPreferences: SharedPrefrenceDB, val isDialogShow:MutableLiveData<Boolean>)
{

    fun getCurrentDayWeatherReport(currentDayResponseMutableLiveData: MutableLiveData<CurrentDayResponse?>,
                                   values:Map<String,Any> ,newtworkErrorListner: NetworkErrorListner):Disposable {
        //isDialogShow.value = true
        //loginUser(@Field(value="key")key:String,@Field(value="email")email:String,@Field(value="password")password:String)
       return apiService.getCurrentDayWather(values).subscribeOn(Schedulers.io())
            .compose(schedulers.applySchedulers())
            .subscribe({ result ->
                isDialogShow.value = false
                currentDayResponseMutableLiveData.postValue(result)
            },
            { throwable ->
                isDialogShow.value = false
                newtworkErrorListner.networkErrorOccur(throwable.message.toString())

            })


    }

    fun getFiveDaysWatherReport(userList: MutableLiveData<FiveDaysWatherResponse?>,values:Map<String,Any> ,newtworkErrorListner: NetworkErrorListner):Disposable {
        isDialogShow.value = true
        //loginUser(@Field(value="key")key:String,@Field(value="email")email:String,@Field(value="password")password:String)
       return  apiService.getFiveDayReport(values).subscribeOn(Schedulers.io())
            .compose(schedulers.applySchedulers())
            .subscribe({ result ->
                isDialogShow.value = false
                userList.postValue(result)
            },
            { throwable ->
                        isDialogShow.value = false
                        newtworkErrorListner.networkErrorOccur(throwable.message.toString())

            })


    }






}