package com.ddd4.dropit.ui.main

import androidx.activity.viewModels
import com.ddd4.dropit.R
import com.ddd4.dropit.base.ui.BaseActivity
import com.ddd4.dropit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()

    override fun setBind() {
        binding.apply {
            mainVM = mainViewModel
        }
    }

    override fun setObserve() {

    }
}
