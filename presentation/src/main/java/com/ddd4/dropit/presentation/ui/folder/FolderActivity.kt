package com.ddd4.dropit.presentation.ui.folder

import android.os.Bundle
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityFolderBinding
import timber.log.Timber

class FolderActivity : BaseActivity<ActivityFolderBinding>(R.layout.activity_folder) {

    override fun setBind() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extra = intent.extras
        val alarmId = extra?.get("alarmId") as Long
        Timber.d("alarmId: $alarmId")
    }
}