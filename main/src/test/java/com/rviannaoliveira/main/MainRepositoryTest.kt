package com.rviannaoliveira.main

import com.rviannaoliveira.cache.ICache
import com.rviannaoliveira.main.data.MainRepository
import com.rviannaoliveira.main.data.MainRepositoryImpl
import com.rviannaoliveira.main.data.service.MainService
import com.rviannaoliveira.networking.domain.model.Item
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MainRepositoryTest {
    private lateinit var repository: MainRepository

    @MockK
    private lateinit var cache : ICache

    @MockK
    private lateinit var service : MainService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = MainRepositoryImpl(service,cache)
    }

    @Test
    fun `get items`() {
        every { service.getItems() } answers  { Single.just(listResponse) }

        repository.getItems()
            .test()
            .onComplete()

        verify {
            service.getItems()
        }

    }

    @Test
    fun `save all list`() {
        every {  cache.put(MainRepositoryImpl.KEY_LIST, list) } answers { Unit}
        repository.saveAllList(list)

        verify {
            cache.put(MainRepositoryImpl.KEY_LIST, list)
        }
    }

    @Test
    fun `get item offset`(){
        every {  cache.get<List<Item>>(MainRepositoryImpl.KEY_LIST) } answers { list }

        val list = repository.getItemOffset(0,2)

        assertTrue(list.size == 2)
    }

    @Test
    fun `get the last time offset`(){
        every {  cache.get<List<Item>>(MainRepositoryImpl.KEY_LIST) } answers { list }

        val list = repository.getItemOffset(0,5)

        assertTrue(list.size == 5)
    }

    @Test
    fun `get empty item offset`(){
        every {  cache.get<List<Item>>(MainRepositoryImpl.KEY_LIST) } answers { list }

        val list = repository.getItemOffset(0,6)

        assertTrue(list.isEmpty())
    }
}