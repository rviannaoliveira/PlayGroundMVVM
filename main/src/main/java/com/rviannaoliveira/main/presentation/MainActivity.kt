package com.rviannaoliveira.main.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rviannaoliveira.base.BaseActivity
import com.rviannaoliveira.main.R
import com.rviannaoliveira.main.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val vm by appViewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = vm
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Colocar o flavor"
    }
}