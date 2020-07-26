package com.ddd4.dropit.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ddd4.core.utils.toast
import com.ddd4.dropit.R
import com.ddd4.dropit.base.DataBindingActivity
import com.ddd4.dropit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDataBinding()
    }

    private fun setupDataBinding(){
        binding.apply{
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel
        }
        this.toast("Hello World")
    }
}