package com.ddd4.dropit.presentation.ui.detailfolder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderItemDetailBinding
import com.ddd4.dropit.presentation.util.Constants
import com.ddd4.dropit.presentation.util.alarm.AlarmReceiver
import com.ddd4.dropit.presentation.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderItemDetailActivity : BaseActivity<ActivityFolderItemDetailBinding>(R.layout.activity_folder_item_detail) {

    private val viewModel: FolderItemDetailViewModel by viewModels()
    private var itemId = -1L

    override fun setBind() {
        binding.apply {
            detailItemViewModel = viewModel
        }

        itemId = getId(intent)
        viewModel.start(itemId)
    }

    override fun setObserve() {
        viewModel.testIsEmpty.observe(this, Observer {
            this.toast(resources.getString(R.string.toast_input_length))
        })

        viewModel.itemDeleteButton.observe(this, Observer {
            this.toast(resources.getString(R.string.toast_delete))
            setResult(resultCode, Intent().apply { putExtra(Constants.EXTRA_DELETE_ITEM ,false) })

            val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AlarmReceiver::class.java).putExtra(ALARM_ID, itemId)
            val pendingIntent = PendingIntent.getBroadcast(
                this, itemId.toInt(), intent, PendingIntent.FLAG_CANCEL_CURRENT)

            pendingIntent?.let{
                alarmManager.cancel(it)
                it.cancel()
            }

            finish()
        })

        viewModel.editCompleteButton.observe(this, Observer {
            this.toast(resources.getString(R.string.toast_edit_complete))
        })

        viewModel.backButton.observe(this, Observer {
            finish()
        })
    }

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_ITEM_ID, -1)
    }

    companion object {
        const val resultCode = 6000
        const val ALARM_ID = "alarmId"
    }
}
