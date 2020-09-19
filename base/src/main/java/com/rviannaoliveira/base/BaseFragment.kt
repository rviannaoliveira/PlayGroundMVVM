package com.rviannaoliveira.base

import com.rviannaoliveira.di.DaggerViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var vmFactory: DaggerViewModelFactory

    inline fun <reified VM : BaseViewModel> appViewModel(): FragmentViewModelDelegate<VM> {
        return FragmentViewModelDelegate(VM::class, this) { this.vmFactory }
    }
}
