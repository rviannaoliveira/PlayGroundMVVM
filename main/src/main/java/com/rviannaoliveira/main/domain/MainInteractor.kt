package com.rviannaoliveira.main.domain

import com.rviannaoliveira.main.data.MainRepository
import com.rviannaoliveira.main.domain.mapper.toListItems
import com.rviannaoliveira.main.domain.model.Item
import io.reactivex.Single
import javax.inject.Inject

interface MainInteractor {
    fun getItems(): Single<List<Item>>
}

class MainInteractorImpl @Inject constructor(
    private val repository: MainRepository) : MainInteractor {

    override fun getItems(): Single<List<Item>> =
        repository.getItems().map {
            it.toListItems()
        }
}