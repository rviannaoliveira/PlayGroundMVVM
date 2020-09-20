package com.rviannaoliveira.detail

import android.content.res.Resources
import com.rviannaoliveira.cache.ICache
import com.rviannaoliveira.detail.data.DetailRepository
import com.rviannaoliveira.detail.data.DetailRepositoryImpl
import com.rviannaoliveira.detail.presentation.DetailUiModel
import com.rviannaoliveira.detail.presentation.DetailUiModelImpl
import com.rviannaoliveira.networking.domain.model.Item
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DetailUiModelTest {
    private lateinit var uiModel: DetailUiModel

    @MockK
    private lateinit var resources: Resources

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setUpAllResources()
        uiModel = DetailUiModelImpl(resources)
    }

    @Test
    fun `get detail item when the item is rental`() {
        uiModel.getDetailItem(item)

        verifyOrder {
            resources.getString(R.string.title_detail)
            resources.getString(R.string.rental)
            resources.getString(R.string.bedrooms)
            resources.getString(R.string.bathrooms)
            resources.getString(R.string.usableAreas)
            resources.getString(R.string.parkingSpaces)
            resources.getString(R.string.price)
        }
    }


    @Test
    fun `get detail item when the item is sell`() {
        uiModel.getDetailItem(item2)

        verifyOrder {
            resources.getString(R.string.title_detail)
            resources.getString(R.string.sell)
            resources.getString(R.string.bedrooms)
            resources.getString(R.string.bathrooms)
            resources.getString(R.string.usableAreas)
            resources.getString(R.string.parkingSpaces)
            resources.getString(R.string.price)
        }
    }

    private fun setUpAllResources() {
        every { resources.getString(R.string.title_detail) } answers { "" }
        every { resources.getString(R.string.rental) } answers { "" }
        every { resources.getString(R.string.sell) } answers { "" }
        every { resources.getString(R.string.bedrooms) } answers { "" }
        every { resources.getString(R.string.bathrooms) } answers { "" }
        every { resources.getString(R.string.parkingSpaces) } answers { "" }
        every { resources.getString(R.string.usableAreas) } answers { "" }
        every { resources.getString(R.string.price) } answers { "" }
        every {
            resources.getString(R.string.title_detail).format(resources.getString(R.string.rental))
        } answers { "" }
        every {
            resources.getString(R.string.title_detail).format(resources.getString(R.string.sell))
        } answers { "" }
    }
}