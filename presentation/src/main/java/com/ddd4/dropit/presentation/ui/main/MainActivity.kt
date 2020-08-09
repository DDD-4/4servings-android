package com.ddd4.dropit.presentation.ui.main

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityMainBinding
import com.ddd4.dropit.presentation.ui.category.CategoryActivity
import com.ddd4.dropit.presentation.ui.folder.FolderActivity
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
        mainViewModel.startFolder.observe(this, Observer { folderId ->
            startActivity(Intent(this, FolderActivity::class.java)
                .putExtra("folderId", folderId))
        })
        mainViewModel.startCategory.observe(this, Observer { categoryId ->
            startActivity(Intent(this, CategoryActivity::class.java)
                .putExtra("categoryId", categoryId))
        })
    }
}
