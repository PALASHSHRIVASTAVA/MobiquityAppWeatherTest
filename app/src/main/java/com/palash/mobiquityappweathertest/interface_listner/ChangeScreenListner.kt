package com.palash.mobiquityappweathertest.interface_listner

import com.palash.mobiquityappweathertest.util.FragmentNameEnum


interface ChangeScreenListner {

    fun <V> changeScreenFun(screenName : FragmentNameEnum, v:V);
    fun showNaveButton(isSho:Boolean)
}