package com.ddd4.dropit.presentation.ui.folder

import android.os.Bundle
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderBinding
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.ui.add.AddActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FolderActivity : BaseActivity<ActivityFolderBinding>(R.layout.activity_folder) {

    private val folderViewModel: FolderViewModel by viewModels()

    override fun setBind() {
        binding.apply{
            folderVM = folderViewModel
        }
    }

    override fun setObserve() {
        folderViewModel.sortByLatestButton.observe(this, Observer {
            Timber.e("sort by latest button clicked")
        })
        folderViewModel.sortByExpirationButton.observe(this, Observer {
            Timber.e("sort by expiration button clicked")
        })
        folderViewModel.floatingButton.observe(this, Observer {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        })
        folderViewModel.nextButton.observe(this, Observer {
            val intent = Intent(this, MoveFolderActivity::class.java)
            startActivity(intent)
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extra = intent.extras
        val alarmId = extra?.get("alarmId") as Long
        Timber.d("alarmId: $alarmId")
    }
}