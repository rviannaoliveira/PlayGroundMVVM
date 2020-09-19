package com.rviannaoliveira.main.presentation

import android.util.Log
import com.rviannaoliveira.base.BaseViewModel
import com.rviannaoliveira.base.Reporter
import com.rviannaoliveira.main.domain.MainInteractor
import com.rviannaoliveira.networking.di.IOScheduler
import com.rviannaoliveira.networking.di.MainScheduler
import io.reactivex.Scheduler
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
    @IOScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler,
    private val reporter: Reporter
) : BaseViewModel() {

    init {
         compositeDisposable.add(interactor.getItems()
             .subscribeOn(ioScheduler)
             .observeOn(mainScheduler)
             .subscribe({ list ->
                 list.forEach {
                     Log.d(">>>>>>>", it.toString() )
                 }
             },{
                 reporter.logError(it)
             }))

    }
}