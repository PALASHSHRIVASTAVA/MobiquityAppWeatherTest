package com.palash.mobiquityappweathertest.utils

import android.content.Context
import android.content.SharedPreferences
import com.palash.mobiquityappweathertest.BuildConfig

class SharedPrefrenceDB(val context: Context)
{
    private val sharedPrefrenceDB: SharedPreferences
    private val sharedPreferencesEditer: SharedPreferences.Editor
    private  val PREFERENCES_FILE_KEY = BuildConfig.APPLICATION_ID

    init{
        sharedPrefrenceDB = context.getSharedPreferences(PREFERENCES_FILE_KEY,Context.MODE_PRIVATE)
        sharedPreferencesEditer = sharedPrefrenceDB.edit()
    }


    fun getStringVal(key:String):String?
    {
        return sharedPrefrenceDB.getString(key,null)
    }

    fun initStringVal(key:String,value:String)
    {
        sharedPreferencesEditer.putString(key,value)
        sharedPreferencesEditer.apply()
    }

    fun clearShredPrefrences()
    {
        sharedPreferencesEditer.clear();
        sharedPreferencesEditer.apply();
    }

}