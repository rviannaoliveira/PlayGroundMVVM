package com.rviannaoliveira.main.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rviannaoliveira.base.BaseActivity
import com.rviannaoliveira.main.R
import com.rviannaoliveira.main.databinding.ActivityMainBinding
import com.rviannaoliveira.main.presentation.adapter.ItemListAdapter
import com.rviannaoliveira.shared.SafeObserver

class MainActivity : BaseActivity() {
    private val vm by appViewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = vm

        binding.toolbar.title = "Colocar o flavor"
        setSupportActionBar(binding.toolbar)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter =
            ItemListAdapter{
                Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
            }
    }

    private fun setupObservers() {
        vm.stateLiveData.observe(this, SafeObserver { state ->
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (state) {
                ItemListState.ShowSuccessView -> {
                    binding.viewFlipper.displayedChild = 0
                }
                ItemListState.ShowErrorView -> {
                    binding.viewFlipper.displayedChild = 1
                }
            }
        })

        vm.itemsLiveData.observe(this, SafeObserver {
            (binding.recyclerView.adapter as ItemListAdapter).list = it
        })
    }
}