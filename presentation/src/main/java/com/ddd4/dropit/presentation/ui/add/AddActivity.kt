package com.ddd4.dropit.presentation.ui.add

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityAddBinding
import com.ddd4.dropit.presentation.util.navigateUpOrFinish
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add.*

@AndroidEntryPoint
class AddActivity : BaseActivity<ActivityAddBinding>(R.layout.activity_add) {

    private val addSharedViewModel: AddSharedViewModel by viewModels()

    override fun setBind() {
        binding.apply {
            addVM = addSharedViewModel
        }
    }

    override fun setInit() {
        setSupportActionBar(toolbar)
        supportActionBar.apply {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
            this?.setHomeAsUpIndicator(R.drawable.ic_back_gray_32dp)
            this?.setDisplayShowTitleEnabled(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.addNavHost).navigateUpOrFinish(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.slide_none, R.anim.slide_end)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if(isFinishing){
            overridePendingTransition(R.anim.slide_none, R.anim.slide_end)
        }
    }
}