package com.rviannaoliveira.main.data

import com.rviannaoliveira.main.data.model.ItemResponse
import com.rviannaoliveira.main.data.service.MainService
import io.reactivex.Single
import javax.inject.Inject

interface MainRepository{
    fun getItems() : Single<List<ItemResponse>>
}

class MainRepositoryImpl @Inject constructor(private val service : MainService) : MainRepository{
    override fun getItems(): Single<List<ItemResponse>> =
        service.getItems()
}