package com.ddd4.dropit.presentation.ui.createfolderdialog

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityCreateFolderDialogBinding
import com.ddd4.dropit.presentation.ui.main.MainActivity
import com.ddd4.dropit.presentation.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFolderDialogActivity : BaseActivity<ActivityCreateFolderDialogBinding>(R.layout.activity_create_folder_dialog) {

    private val viewModel: CreateFolderDialogViewModel by viewModels()
    private var itemIdList = arrayListOf<Long>()

    override fun setBind() {
        binding.apply{
            createDialogVM = viewModel
        }
        itemIdList = getIdList(intent)
        viewModel.start(itemIdList)
    }

    override fun setObserve() {
        viewModel.confirmButton.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

        viewModel.cancelButton.observe(this, Observer {
            finish()
        })
    }

    private fun getIdList(intent: Intent): ArrayList<Long> {
        return intent.getIntegerArrayListExtra(Constants.EXTRA_NAME_ITEM_ID) as ArrayList<Long> ?: arrayListOf()
    }

}