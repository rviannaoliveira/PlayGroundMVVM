package com.rviannaoliveira.detail

import com.rviannaoliveira.cache.ICache
import com.rviannaoliveira.detail.data.DetailRepository
import com.rviannaoliveira.detail.data.DetailRepositoryImpl
import com.rviannaoliveira.networking.domain.model.Item
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DetailRepositoryTest {
    private lateinit var repository: DetailRepository

    @MockK
    private lateinit var cache : ICache

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = DetailRepositoryImpl(cache)
    }

    @Test
    fun `get item`() {
        every { cache.get<MutableList<Item>>(DetailRepositoryImpl.KEY_LIST) } answers  { list }

        assertTrue(repository.getItem(id) == item)
        verify {
            cache.get<MutableList<Item>>(DetailRepositoryImpl.KEY_LIST)
        }

    }
}