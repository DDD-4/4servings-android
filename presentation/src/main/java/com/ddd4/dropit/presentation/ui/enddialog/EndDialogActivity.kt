package com.ddd4.dropit.presentation.ui.enddialog

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityEndDialogBinding
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.createfolderdialog.CreateFolderDialogViewModel
import com.ddd4.dropit.presentation.ui.folder.FolderActivity
import com.ddd4.dropit.presentation.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndDialogActivity : BaseActivity<ActivityEndDialogBinding>(R.layout.activity_end_dialog) {

    private val viewModel: EndDialogViewModel by viewModels()
    private var itemId = -1L

    override fun setBind() {
        binding.apply {
            endDialogVM = viewModel
        }
        itemId = getId(intent)
        viewModel.start(itemId)
    }

    @SuppressLint("SetTextI18n")
    override fun setObserve() {
        viewModel.itemName.observe(this, Observer { itemName ->
            binding.tvAlert.text = "$itemName " + resources.getString(R.string.ask_drop_item)
        })

        viewModel.cancelButton.observe(this, Observer {
            finish()
        })

        viewModel.confirmButton.observe(this, Observer {
            startActivity(Intent(this, FolderActivity::class.java))

        })
    }

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_ITEM_ID, -1)
    }
}