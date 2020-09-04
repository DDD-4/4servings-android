package com.ddd4.dropit.presentation.ui.moveFolder

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderBinding
import com.ddd4.dropit.presentation.databinding.ActivityMoveFolderBinding
import com.ddd4.dropit.presentation.dialog.DialogActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoveFolderActivity : BaseActivity<ActivityMoveFolderBinding>(R.layout.activity_move_folder) {

    private val viewModel: MoveFolderViewModel by viewModels()

    override fun setBind() {
        binding.apply {
            moveFolderVM = viewModel
        }
        viewModel.start()
    }

    override fun setObserve() {
        viewModel.newFolderButton.observe(this, Observer {
            startActivityForResult(
                Intent(this, DialogActivity::class.java), 1500
            )
        })
    }
}
