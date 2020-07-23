package com.ddd4.dropit.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ddd4.dropit.R
import com.ddd4.dropit.base.DataBindingActivity
import com.ddd4.dropit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {
    private val mBinding: ActivityMainBinding by binding(R.layout.activity_main)
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDataBinding()
    }

    private fun setupDataBinding(){
        mBinding.apply{
            lifecycleOwner = this@MainActivity
            mainViewModel = mViewModel
        }
    }
}