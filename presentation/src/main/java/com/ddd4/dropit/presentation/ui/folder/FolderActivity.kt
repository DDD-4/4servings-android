package com.ddd4.dropit.presentation.ui.folder

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderBinding
import com.ddd4.dropit.presentation.dialog.DialogActivity
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.detailFolder.FolderItemDetailActivity
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderActivity
import com.ddd4.dropit.presentation.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class FolderActivity : BaseActivity<ActivityFolderBinding>(R.layout.activity_folder) {

    private val folderViewModel: FolderViewModel by viewModels()
    private lateinit var listAdapter: FolderAdapter
    private var folderId = -1L

    override fun setBind() {
        binding.apply {
            folderVM = folderViewModel
        }
        folderId = getId(intent)
        setupAdapter()
    }

    override fun setObserve() {
        folderViewModel.sortByLatestButton.observe(this, Observer {
            binding.rvDetailFolder.adapter?.notifyDataSetChanged()
        })

        folderViewModel.sortByExpirationButton.observe(this, Observer {
            binding.rvDetailFolder.adapter?.notifyDataSetChanged()
        })

        folderViewModel.floatingButton.observe(this, Observer {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        })

        folderViewModel.nextButton.observe(this, Observer {
            startActivityForResult(
                Intent(this, MoveFolderActivity::class.java)
                    .putExtra(Constants.ITEM_ID, it),
                1500
            )
        })

        folderViewModel.folderItems.observe(this, Observer {
            binding.rvDetailFolder.adapter?.notifyDataSetChanged()
        })

        folderViewModel.backButton.observe(this, Observer {
            finish()
        })

        folderViewModel.item.observe(this, Observer { item ->
            startActivityForResult(
                Intent(this, FolderItemDetailActivity::class.java)
                    .putExtra(Constants.EXTRA_NAME_ITEM_ID, item),
                3030)

        })

        folderViewModel.test.observe(this, Observer {
            listAdapter.selectClearItems()
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

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_FOLDER_ID, -1)
    }

    //TODO FIX
    private fun setupAdapter() {
        listAdapter = FolderAdapter(folderViewModel, folderViewModel.onItemClickListener)
        binding.rvDetailFolder.layoutManager = GridLayoutManager(this, 3)
        binding.rvDetailFolder.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()
        folderViewModel.start(folderId)
        binding.rvDetailFolder.adapter?.notifyDataSetChanged()
    }
}