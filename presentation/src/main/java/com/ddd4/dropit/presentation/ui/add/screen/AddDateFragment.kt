package com.ddd4.dropit.presentation.ui.add.screen

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddDateBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import com.ddd4.dropit.presentation.util.alarm.AlarmReceiver
import com.ddd4.dropit.presentation.util.alarm.AlarmRegistUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_date.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddDateFragment : BaseFragment<FragmentAddDateBinding>(R.layout.fragment_add_date) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    @Inject
    lateinit var alarmRegistUtil: AlarmRegistUtil

    override fun setBind() {
        binding.apply {
            view = this@AddDateFragment
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            addSharedViewModel.setItem()
        })
        addSharedViewModel.addComplete.observe(this, Observer { alarmData ->
            alarmRegistUtil.setAlarm(alarmData.alarmId, alarmData.time)
            requireActivity().finish()
        })
    }

    override fun setInit() {
        rbtnLittleDate.setOnClickListener {
            addSharedViewModel.setLittleState()
        }
        rbtnDontDate.setOnClickListener {
            addSharedViewModel.setDontState()
        }
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(100)
    }

    val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        addSharedViewModel.setKnowDate(year, month, dayOfMonth)
    }

    val littleDateWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            addSharedViewModel.setLittleDate(s.toString())
        }
    }

//    private fun setAlarm(alarmId: Long, alarmTime: Long) {
//
//        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val intent = Intent(requireActivity(), AlarmReceiver::class.java).putExtra("alarmId", alarmId)
//        val pendingIntent = PendingIntent.getBroadcast(
//            requireActivity(), alarmId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val calendar = Calendar.getInstance()
//        calendar.time = Date(alarmId)
//        calendar.set(Calendar.HOUR_OF_DAY, 11)
//        calendar.set(Calendar.MINUTE, 30)
//        val triggerTime = calendar.timeInMillis
//
//        val testTime = (SystemClock.elapsedRealtime() + 10 * 1000)
//        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, testTime, pendingIntent)
//    }
}