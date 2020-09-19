package com.rviannaoliveira.base

import com.rviannaoliveira.di.DaggerViewModelFactory
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

open class BaseDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var vmFactory: DaggerViewModelFactory

    inline fun <reified VM : BaseViewModel> appViewModel(): FragmentViewModelDelegate<VM> {
        return FragmentViewModelDelegate(VM::class, this) { this.vmFactory }
    }
}
