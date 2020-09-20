package com.rviannaoliveira.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rviannaoliveira.base.BaseViewModel
import com.rviannaoliveira.detail.domain.DetailInteractor
import com.rviannaoliveira.detail.domain.DetailItem
import com.rviannaoliveira.networking.domain.model.GeoLocation
import com.rviannaoliveira.networking.domain.model.Item
import com.rviannaoliveira.networking.domain.model.Location
import javax.inject.Inject


class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
    private val uiModel: DetailUiModel
) : BaseViewModel() {
    @Suppress("JoinDeclarationAndAssignment")
    private var item : Item

    private val _urlsLiveData = MutableLiveData<List<String>>()
    val urlsLiveData: LiveData<List<String>>
        get() = _urlsLiveData

    private val _itemLiveData = MutableLiveData<DetailItem>()
    val itemLiveData: LiveData<DetailItem>
        get() = _itemLiveData

    private val _openMapsLiveData = MutableLiveData<Location>()
    val openMapsLiveData: LiveData<Location>
        get() = _openMapsLiveData

    init {
        item = interactor.getItem()
        _urlsLiveData.value = item.images
        _itemLiveData.value = uiModel.getDetailItem(item)
    }

    fun openGoogleMaps(){
        _openMapsLiveData.value = item.address.geoLocation.location
    }
}