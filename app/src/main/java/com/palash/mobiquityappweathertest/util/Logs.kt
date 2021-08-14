package com.palash.mobiquityappweathertest.util

import android.util.Log

class Logs {

    companion object {
        @JvmStatic
        fun e(source:String,target:String) {
            Log.e(source,target)
        }

        @JvmStatic
        fun v(source:String,target:String) {
            Log.v(source,target)
        }

        @JvmStatic
        fun d(source:String,target:String) {
            Log.d(source,target)
        }
    }
}