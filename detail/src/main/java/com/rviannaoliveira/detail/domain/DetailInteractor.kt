package com.rviannaoliveira.detail.domain

import com.rviannaoliveira.detail.data.DetailRepository
import com.rviannaoliveira.detail.di.ID
import com.rviannaoliveira.networking.domain.model.Item
import javax.inject.Inject

interface DetailInteractor {
    fun getItem() : Item
}

class DetailInteractorImpl @Inject constructor(
    private val repository: DetailRepository,
    @ID private val id : String
) : DetailInteractor {

    override fun getItem(): Item =
        repository.getItem(id)
}