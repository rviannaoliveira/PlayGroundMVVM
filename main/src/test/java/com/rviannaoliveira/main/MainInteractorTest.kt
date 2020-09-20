package com.rviannaoliveira.main

import com.rviannaoliveira.main.data.MainRepository
import com.rviannaoliveira.main.domain.MainInteractor
import com.rviannaoliveira.main.domain.MainInteractorImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MainInteractorTest {
    private lateinit var interactor: MainInteractor

    @MockK
    private lateinit var repository: MainRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = MainInteractorImpl(repository)
    }

    @Test
    fun `get item`() {
        every { repository.getItems() } answers { Single.just(listResponse) }
        every { repository.saveAllList(listMapper) } answers { Unit }

        interactor.getItems()
            .test()
            .assertValue {
                listMapper == it
            }
            .onComplete()

        verifyOrder {
            repository.getItems()
            repository.saveAllList(listMapper)
        }

    }

    @Test
    fun `get item until 20`() {
        every { repository.getItems() } answers { Single.just(listResponse20) }
        every { repository.saveAllList(listMapper20) } answers { Unit }

        interactor.getItems()
            .test()
            .assertValue {
                it.size == 20
            }
            .onComplete()

        verifyOrder {
            repository.getItems()
            repository.saveAllList(listMapper20)
        }
    }

    @Test
    fun `get item offset when is null`() {
        assertTrue(interactor.getItemOffset(null).isEmpty())
    }


    @Test
    fun `get item offset`() {
        every { repository.getItemOffset(list.size, list.size + 20) } answers { listOf() }

        interactor.getItemOffset(list)

        verify {
            repository.getItemOffset(list.size, list.size + 20)
        }
    }
}