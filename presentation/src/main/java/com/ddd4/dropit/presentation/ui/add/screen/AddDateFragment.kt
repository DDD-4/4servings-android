package com.ddd4.dropit.presentation.ui.add.screen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.SystemClock
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddDateBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import com.ddd4.dropit.presentation.util.alarm.AlarmReceiver
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddDateFragment : BaseFragment<FragmentAddDateBinding>(R.layout.fragment_add_date) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    override fun setBind() {
        binding.apply {
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            addSharedViewModel.setItem()
        })
        addSharedViewModel.addComplete.observe(this, Observer { alarmId ->
            setAlarm(alarmId)
            requireActivity().finish()
        })
    }

    private fun setAlarm(alarmId: Long) {
        val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), AlarmReceiver::class.java).putExtra("alarmId", alarmId)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(), alarmId.toInt(), intent, PendingIntent.FLAG_CANCEL_CURRENT)

        //triggerTime(알람시간)은 alarmId(Date time)로 시간을 계산
        val calendar = Calendar.getInstance()
        calendar.time = Date(alarmId)
        calendar.set(Calendar.HOUR_OF_DAY, 11)
        calendar.set(Calendar.MINUTE, 30)
        val triggerTime = calendar.timeInMillis

//        val textTriggerTime = (System.currentTimeMillis() + 30 * 1000)
//        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, textTriggerTime, pendingIntent)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(100)
    }
}