package com.rviannaoliveira.base

import android.util.Log
import javax.inject.Inject

interface Reporter {
    fun logError(throwable: Throwable)
}

class ReporterImpl @Inject constructor() : Reporter{
    override fun logError(throwable : Throwable) {
       Log.d(">>>>>>",throwable.message ?: "ERROR")
        if(BuildConfig.DEBUG){
            throwable.printStackTrace()
        }
    }
}