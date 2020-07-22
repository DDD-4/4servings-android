package com.ddd4.dropit.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddd4.dropit.R
import com.ddd4.dropit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    //private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    //private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
