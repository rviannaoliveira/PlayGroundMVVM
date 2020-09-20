package com.rviannaoliveira.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rviannaoliveira.base.BaseViewModel
import com.rviannaoliveira.base.Reporter
import com.rviannaoliveira.main.domain.MainInteractor
import com.rviannaoliveira.main.domain.model.Item
import com.rviannaoliveira.networking.di.IOScheduler
import com.rviannaoliveira.networking.di.MainScheduler
import io.reactivex.Scheduler
import javax.inject.Inject

sealed class ItemListState(val showLoading: Boolean) {
    object ShowSuccessView : ItemListState(false)
    object ShowErrorView : ItemListState(false)
    object ShowLoading : ItemListState(true)
    object RemoveLoading : ItemListState(false)
}

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
    @IOScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler,
    private val reporter: Reporter
) : BaseViewModel() {
    private val _stateLiveData = MutableLiveData<ItemListState>()
    val stateLiveData: LiveData<ItemListState>
        get() = _stateLiveData

    private val _itemsLiveData = MutableLiveData<List<Item>>()
    val itemsLiveData: LiveData<List<Item>>
        get() = _itemsLiveData

    init {
        loadItems()
    }

    private fun loadItems() {
        compositeDisposable.clear()
        compositeDisposable.add(interactor.getItems()
            .doOnSubscribe { _stateLiveData.postValue(ItemListState.ShowLoading) }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doFinally { _stateLiveData.postValue(ItemListState.RemoveLoading) }
            .subscribe({ list ->
                _stateLiveData.value = ItemListState.ShowSuccessView
                _itemsLiveData.value = list
                list.forEach {
                    Log.d(">>>>>>>", it.toString())
                }
            }, {
                _stateLiveData.value = ItemListState.ShowErrorView
                reporter.logError(it)
            })
        )
    }

    fun retry() {
        loadItems()
    }
}