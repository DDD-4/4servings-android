package com.ddd4.dropit.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityMainBinding
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.category.CategoryActivity
import com.ddd4.dropit.presentation.ui.folder.FolderActivity
import com.ddd4.dropit.presentation.util.Constants
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
        mainViewModel.categoryClick.observe(this, Observer { categoryId ->
            startActivity(Intent(this, CategoryActivity::class.java)
                .putExtra(Constants.EXTRA_NAME_CATEGORY_ID, categoryId))
        })
        mainViewModel.folderClick.observe(this, Observer { folderId ->
            startActivity(Intent(this, FolderActivity::class.java)
                .putExtra(Constants.EXTRA_NAME_FOLDER_ID, folderId))
        })
        mainViewModel.addClick.observe(this, Observer {
            startActivity(Intent(this, AddActivity::class.java))
            overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val extra = intent.extras
//        if (extra != null) {
//            val alarmId = extra["alarmId"] as Long
//            //알람을 통해서 들어왔다면 alarmId로 해당 알람을 편집할 수 있는 화면으로 이동
//            startActivity(Intent(this, FolderActivity::class.java).putExtra("alarmId", alarmId))
//        }
    }

    override fun onStart() {
        super.onStart()

        mainViewModel.getFolderItems()
    }
}
