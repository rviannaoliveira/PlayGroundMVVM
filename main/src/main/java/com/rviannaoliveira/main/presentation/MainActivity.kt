package com.rviannaoliveira.main.presentation

import android.os.Bundle
import com.rviannaoliveira.base.BaseActivity

class MainActivity : BaseActivity() {
    private val vm by appViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}