package com.ddd4.dropit.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivitySplashBinding
import com.ddd4.dropit.presentation.ui.category.CategoryActivity
import com.ddd4.dropit.presentation.ui.main.MainActivity
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun setBind() {
        binding.apply {
            splashVM = viewModel
        }
    }

    override fun setObserve() {
        viewModel.navigateToMain.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        })
    }
}