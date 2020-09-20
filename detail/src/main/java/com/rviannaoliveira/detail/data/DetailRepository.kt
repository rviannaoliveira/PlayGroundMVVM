package com.rviannaoliveira.detail.data

import com.rviannaoliveira.cache.ICache
import com.rviannaoliveira.networking.domain.model.Item
import javax.inject.Inject

interface DetailRepository {
    fun getItem(id : String) : Item
}

class DetailRepositoryImpl @Inject constructor(
    private val cache : ICache
) : DetailRepository{

    override fun getItem(id : String): Item {
        val list = cache.get<MutableList<Item>>(KEY_LIST)!!
        return list.find { id == it.id }!!
    }
    companion object {
        internal const val KEY_LIST = "KEY_LIST"
    }
}