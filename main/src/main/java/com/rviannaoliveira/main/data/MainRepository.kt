package com.rviannaoliveira.main.data

import com.rviannaoliveira.cache.ICache
import com.rviannaoliveira.main.data.model.ItemResponse
import com.rviannaoliveira.main.data.service.MainService
import com.rviannaoliveira.networking.domain.model.Item
import io.reactivex.Single
import javax.inject.Inject

interface MainRepository {
    fun getItems(): Single<List<ItemResponse>>
    fun saveAllList(list: List<Item>)
    fun getItemOffset(initialOffset: Int, offset: Int): List<Item>
}

class MainRepositoryImpl @Inject constructor(
    private val service: MainService,
    private val cache: ICache
) : MainRepository {

    override fun getItems(): Single<List<ItemResponse>> =
        service.getItems()

    override fun saveAllList(list: List<Item>) {
        cache.put(KEY_LIST, list)
    }

    override fun getItemOffset(initialOffset: Int, offset: Int): List<Item> {
        val list = cache.get<List<Item>>(KEY_LIST) ?: emptyList()

        if (offset > list.size && offset != list.size) {
            return emptyList()
        }

        if (offset == list.size) {
            return list.subList(initialOffset, list.size)
        }

        return list.subList(initialOffset, offset)
    }

    companion object {
        private const val KEY_LIST = "KEY_LIST"
    }
}