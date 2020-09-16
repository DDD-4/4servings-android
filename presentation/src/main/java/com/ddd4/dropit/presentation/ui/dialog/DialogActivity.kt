package com.ddd4.dropit.presentation.ui.dialog

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
class DialogActivity : BaseActivity<ActivityCreateFolderDialogBinding>(R.layout.activity_create_folder_dialog) {

    private val viewModel: DialogViewModel by viewModels()
    private var itemIdList = arrayListOf<Long>()


    override fun setBind() {
        binding.apply{
            dialogViewModel = viewModel
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
        return intent.getIntegerArrayListExtra(Constants.ITEM_ID) as ArrayList<Long> ?: arrayListOf()
    }

}