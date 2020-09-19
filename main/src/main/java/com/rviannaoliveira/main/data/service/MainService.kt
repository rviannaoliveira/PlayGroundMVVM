package com.rviannaoliveira.main.data.service

import com.rviannaoliveira.main.data.model.ItemResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MainService{
    @GET("source-1.json")
    fun getItems() : Single<List<ItemResponse>>
}