package com.palash.mobiquityappweathertest.interface_listner

interface RecylerViewClickListner {

    abstract fun onItemClick(pos:Int)
    fun removeItem(cityName:String)
}