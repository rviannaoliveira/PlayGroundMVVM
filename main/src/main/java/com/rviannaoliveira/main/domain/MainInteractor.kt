package com.rviannaoliveira.main.domain

import com.rviannaoliveira.main.data.MainRepository
import com.rviannaoliveira.main.domain.mapper.toListItems
import com.rviannaoliveira.main.domain.model.Item
import io.reactivex.Single
import javax.inject.Inject

interface MainInteractor {
    fun getItems(): Single<List<Item>>
    fun getItemOffset(currentList: List<Item>?): List<Item>
}

class MainInteractorImpl @Inject constructor(
    private val repository: MainRepository
) : MainInteractor {

    override fun getItems(): Single<List<Item>> =
        repository.getItems().map {
            val list = it.toListItems()
            repository.saveAllList(list)
            list.take(20)
        }

    override fun getItemOffset(currentList: List<Item>?): List<Item> {
        if (currentList == null) {
            return emptyList()
        }

        val currentMutableList = currentList.toMutableList()
        val newList = repository.getItemOffset(currentList.size, currentList.size + BLOCK)

        currentMutableList.addAll(newList)
        return currentMutableList
    }

    companion object{
        private const val BLOCK = 20
    }
}