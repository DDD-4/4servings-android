package com.ddd4.dropit.presentation.ui.folder

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderBinding
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.detailfolder.FolderItemDetailActivity
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderActivity
import com.ddd4.dropit.presentation.util.Constants
import com.ddd4.dropit.presentation.util.hideButton
import com.ddd4.dropit.presentation.util.showButton
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.days


@AndroidEntryPoint
class FolderActivity : BaseActivity<ActivityFolderBinding>(R.layout.activity_folder) {

    private val folderViewModel: FolderViewModel by viewModels()
    private var folderId = -1L

    override fun setBind() {
        binding.apply {
            folderVM = folderViewModel
        }
        folderId = getId(intent)
    }

    override fun setObserve() {

        folderViewModel.floatingButton.observe(this, Observer {
            startActivity(Intent(this, AddActivity::class.java))
            overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        })

        folderViewModel.nextButton.observe(this, Observer {
            startActivityForResult(
                Intent(this, MoveFolderActivity::class.java)
                    .putExtra(Constants.EXTRA_NAME_ITEM_ID, it),
                Constants.INTENT_MOVE_FOLDER
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
                Constants.INTENT_ITEM_DETAIL)

        })

        folderViewModel.selectImageButton.observe(this, Observer {
            binding.ibSelectImage.text = it
            when(it) {
                resources.getString(R.string.select) -> {
                    binding.folderFloatingButton.showButton()
                    binding.folderRectangleButton.hideButton()
                }
                resources.getString(R.string.cancel) -> {
                    binding.folderFloatingButton.hideButton()
                    binding.folderRectangleButton.showButton()
                }
            }
        })

        folderViewModel.clearSelected.observe(this, Observer {
            binding.rvDetailFolder.adapter?.notifyDataSetChanged()
        })
    }

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_FOLDER_ID, -1)
    }

    override fun onResume() {
        super.onResume()
        folderViewModel.start(folderId)
        binding.rvDetailFolder.adapter?.notifyDataSetChanged()
    }
}