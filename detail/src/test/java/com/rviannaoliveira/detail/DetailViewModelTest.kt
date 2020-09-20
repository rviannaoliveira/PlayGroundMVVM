package com.rviannaoliveira.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rviannaoliveira.detail.domain.DetailInteractor
import com.rviannaoliveira.detail.domain.DetailItem
import com.rviannaoliveira.detail.presentation.DetailUiModel
import com.rviannaoliveira.detail.presentation.DetailViewModel
import com.rviannaoliveira.networking.domain.model.Location
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var vm: DetailViewModel

    @MockK
    private lateinit var interactor: DetailInteractor

    @MockK
    private lateinit var uiModel: DetailUiModel

    @RelaxedMockK
    private lateinit var urlsLiveData: Observer<List<String>>

    @RelaxedMockK
    private lateinit var detailItemLiveData: Observer<DetailItem>

    @RelaxedMockK
    private lateinit var openMapsLiveData: Observer<Location>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { interactor.getItem() } answers { item }
        every { uiModel.getDetailItem(item) } answers { detailItem }

        vm = DetailViewModel(interactor, uiModel)
        vm.urlsLiveData.observeForever(urlsLiveData)
        vm.itemLiveData.observeForever(detailItemLiveData)
        vm.openMapsLiveData.observeForever(openMapsLiveData)
    }

    @Test
    fun `show screen when it load`() {
        verify {
            urlsLiveData.onChanged(item.images)
            detailItemLiveData.onChanged(detailItem)
        }

    }

    @Test
    fun `show google maps when the user clicked on the button`() {
        vm.openGoogleMaps()

        verify {
            urlsLiveData.onChanged(item.images)
            openMapsLiveData.onChanged(item.address.geoLocation.location)
        }

    }

}
