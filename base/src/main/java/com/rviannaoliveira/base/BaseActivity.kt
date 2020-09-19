package com.rviannaoliveira.base

import com.rviannaoliveira.di.DaggerViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var vmFactory: DaggerViewModelFactory

    inline fun <reified VM : BaseViewModel> appViewModel(): ViewModelDelegate<VM> {
        return ViewModelDelegate(VM::class, this) { this.vmFactory }
    }
}
