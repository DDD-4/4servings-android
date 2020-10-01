package com.ddd4.dropit.presentation.ui.folder

import android.content.Intent
import android.view.View
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

    private val viewModel: FolderViewModel by viewModels()
    private var folderId = -1L

    override fun setBind() {
        binding.apply {
            folderVM = viewModel
        }
        folderId = getId(intent)
    }

    override fun setObserve() {

        viewModel.floatingButton.observe(this, Observer {
            startActivity(Intent(this, AddActivity::class.java))
            overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        })

        viewModel.nextButton.observe(this, Observer {
            startActivityForResult(
                Intent(this, MoveFolderActivity::class.java)
                    .putExtra(Constants.EXTRA_NAME_ITEM_ID, it),
                Constants.INTENT_MOVE_FOLDER
            )
        })

        viewModel.folderItems.observe(this, Observer {
            binding.rvDetailFolder.adapter?.notifyDataSetChanged()
        })

        viewModel.backButton.observe(this, Observer {
            finish()
        })

        viewModel.item.observe(this, Observer { item ->
            startActivityForResult(
                Intent(this, FolderItemDetailActivity::class.java)
                    .putExtra(Constants.EXTRA_NAME_ITEM_ID, item),
                Constants.INTENT_ITEM_DETAIL)

        })

        viewModel.selectImageMode.observe(this, Observer {
            when(it) {
                true -> {
                    binding.folderRectangleButton.visibility = View.GONE
                    binding.folderFloatingButton.showButton()
                    binding.folderRectangleButton.hideButton()
                }
                false -> {
                    binding.folderRectangleButton.visibility = View.VISIBLE
                    binding.folderFloatingButton.hideButton()
                    binding.folderRectangleButton.showButton()
                }
            }
        })

        viewModel.clearSelected.observe(this, Observer {
            binding.rvDetailFolder.adapter?.notifyDataSetChanged()
        })
    }

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_FOLDER_ID, -1)
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(folderId)
        binding.rvDetailFolder.adapter?.notifyDataSetChanged()
    }
}