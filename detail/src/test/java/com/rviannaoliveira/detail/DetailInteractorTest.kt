package com.rviannaoliveira.detail

import com.rviannaoliveira.detail.data.DetailRepository
import com.rviannaoliveira.detail.domain.DetailInteractor
import com.rviannaoliveira.detail.domain.DetailInteractorImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DetailInteractorTest {
    private lateinit var interactor: DetailInteractor

    @MockK
    private lateinit var repository: DetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = DetailInteractorImpl(repository, id)
    }

    @Test
    fun `get item`() {
        every { repository.getItem(id) } answers { item }

        interactor.getItem()

        verify {
            repository.getItem(id)
        }

    }
}