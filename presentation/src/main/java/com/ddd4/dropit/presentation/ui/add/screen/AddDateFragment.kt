package com.ddd4.dropit.presentation.ui.add.screen

import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddDateBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import com.ddd4.dropit.presentation.util.alarm.AlarmRegistUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_date.*
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
}