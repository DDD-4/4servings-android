package com.ddd4.dropit.presentation.ui.folder

import android.os.Bundle
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderBinding
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.detailFolder.FolderItemDetailActivity
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class FolderActivity : BaseActivity<ActivityFolderBinding>(R.layout.activity_folder) {

    private val folderViewModel: FolderViewModel by viewModels()
    private lateinit var listAdapter: FolderAdapter

    override fun setBind() {
        binding.apply {
            folderVM = folderViewModel
        }
        setupAdapter()
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
            Toast.makeText(this, "${it.size}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MoveFolderActivity::class.java)
            startActivity(intent)
        })

        folderViewModel.item.observe(this, Observer {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, FolderItemDetailActivity::class.java)
            startActivity(intent)
        })

        folderViewModel.selectImageButton.observe(this, Observer {
            binding.ibSelectImage.text = it
            when(it) {
                resources.getString(R.string.select) -> {
                    binding.folderFloatingButton.animate().translationY(0f)
                    binding.folderRectangleButton.animate().translationY(300f)
                }
                resources.getString(R.string.cancel) -> {
                    binding.folderFloatingButton.animate().translationY(300f)
                    binding.folderRectangleButton.animate().translationY(0f)
                }
            }
        })
    }

    //TODO FIX
    private fun setupAdapter() {
        listAdapter = FolderAdapter(folderViewModel, folderViewModel.onItemClickListener)
        binding.rvDetailFolder.layoutManager = GridLayoutManager(this, 3)
        binding.rvDetailFolder.adapter = listAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extra = intent.extras
        val alarmId = extra?.get("alarmId") as Long
        Timber.d("alarmId: $alarmId")
    }
}