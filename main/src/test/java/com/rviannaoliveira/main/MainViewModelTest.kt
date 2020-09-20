package com.rviannaoliveira.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rviannaoliveira.base.Reporter
import com.rviannaoliveira.detail.domain.DetailInteractor
import com.rviannaoliveira.detail.domain.DetailItem
import com.rviannaoliveira.detail.presentation.DetailUiModel
import com.rviannaoliveira.detail.presentation.DetailViewModel
import com.rviannaoliveira.main.domain.MainInteractor
import com.rviannaoliveira.main.presentation.ItemListState
import com.rviannaoliveira.main.presentation.MainViewModel
import com.rviannaoliveira.networking.di.IOScheduler
import com.rviannaoliveira.networking.di.MainScheduler
import com.rviannaoliveira.networking.domain.model.Item
import com.rviannaoliveira.networking.domain.model.Location
import com.rviannaoliveira.shared.extensions.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var vm: MainViewModel

    @MockK
    private lateinit var interactor: MainInteractor

    @MockK
    private lateinit var reporter: Reporter

    private val ioScheduler = Schedulers.trampoline()
    private val mainScheduler = Schedulers.trampoline()
    private val exception = Exception()

    @RelaxedMockK
    private lateinit var stateLiveData: Observer<ItemListState>

    @RelaxedMockK
    private lateinit var itemsLiveData: Observer<List<Item>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `show screen when it load`() {
        setupGoodWay()

        verify {
            interactor.getItems()
            itemsLiveData.onChanged(list)
            stateLiveData.onChanged(ItemListState.RemoveLoading)
        }
    }

    @Test
    fun `something is wrong`() {
        setupBadWay()

        verify {
            interactor.getItems()
            reporter.logError(exception)
        }
    }

    @Test
    fun `open detail`(){
        setupGoodWay()

        vm.openDetail(id)

        verify {
            stateLiveData.onChanged(ItemListState.OpenDetail(id))
        }
    }

    @Test
    fun `on last item visible`(){
        setupGoodWay()
        every { interactor.getItemOffset(list) } answers { list }

        vm.onLastItemVisible(1)

        verify {
            itemsLiveData.onChanged(list)
        }
    }

    @Test
    fun `retry whe something is happen of wrong`(){
        setupGoodWay()
        vm.retry()

        verify {
            interactor.getItems()
            itemsLiveData.onChanged(list)
        }
    }

    private fun setupGoodWay() {
        every { interactor.getItems() } answers { Single.just(list) }
        buildViewModel()
    }

    private fun setupBadWay() {
        every { interactor.getItems() } answers { Single.error(exception) }
        buildViewModel()
    }

    private fun buildViewModel() {
        vm = MainViewModel(interactor, ioScheduler, mainScheduler, reporter)
        vm.itemsLiveData.observeForever(itemsLiveData)
        vm.stateLiveData.observeForever(stateLiveData)
    }

}
