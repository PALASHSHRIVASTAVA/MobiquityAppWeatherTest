package com.palash.mobiquityappweathertest.utils


const val PARCEBLE_PARAM1 = "PARCEBLE_PARAM1"
const val PARCEBLE_PARAM2 = "PARCEBLE_PARAM2"
const val LOGIN_RESPONSE_DB = "LOGIN_RESPONSE_DB"

fun isValueNullOREmpty(msg:String):Boolean
{
     if(msg.trim().length>0)
     {
         return true
     }
    return false

}