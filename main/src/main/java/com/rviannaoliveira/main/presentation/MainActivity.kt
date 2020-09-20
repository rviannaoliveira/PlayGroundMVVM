package com.rviannaoliveira.main.presentation

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rviannaoliveira.base.BaseActivity
import com.rviannaoliveira.detail.presentation.DetailActivity
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

        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter =
            ItemListAdapter {
                vm.openDetail(it)
            }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager
                if (linearLayoutManager is LinearLayoutManager) {

                    binding.recyclerView.post {
                        vm.onLastItemVisible(linearLayoutManager.findLastVisibleItemPosition())
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        vm.stateLiveData.observe(this, SafeObserver { state ->
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (state) {
                is ItemListState.ShowSuccessView -> {
                    binding.viewFlipper.displayedChild = 0
                }
                is ItemListState.ShowErrorView -> {
                    binding.viewFlipper.displayedChild = 1
                }
                is ItemListState.OpenDetail -> {
                    startActivity(DetailActivity.newInstance(this, state.id))
                }
            }
        })


        vm.itemsLiveData.observe(this, SafeObserver {
            (binding.recyclerView.adapter as ItemListAdapter).list = it
        })
    }
}