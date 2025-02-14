package com.rviannaoliveira.detail.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.rviannaoliveira.base.BaseActivity
import com.rviannaoliveira.detail.R
import com.rviannaoliveira.detail.databinding.ActivityDetailBinding
import com.rviannaoliveira.shared.SafeObserver

class DetailActivity : BaseActivity() {
    private val vm by appViewModel<DetailViewModel>()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.vm = vm

        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setupObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    private fun setupObservers() {
        vm.urlsLiveData.observe(this, SafeObserver {
            binding.pager.adapter = ImageAdapter(this, it)
            TabLayoutMediator(binding.tabLayout, binding.pager) { _, _ ->
            }.attach()
        })

        vm.openMapsLiveData.observe(this, SafeObserver {
            val gmmIntentUri = Uri.parse("geo:${it.lat},${it.lon}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        })
    }

    companion object {
        internal const val ID = "ID"

        fun newInstance(context: Context, id: String): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(ID, id)
            }
    }
}