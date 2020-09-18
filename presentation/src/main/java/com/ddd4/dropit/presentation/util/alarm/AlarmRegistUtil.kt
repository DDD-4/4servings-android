package com.ddd4.dropit.presentation.util.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@ActivityScoped
class AlarmRegistUtil @Inject constructor(
    @ActivityContext private val context: Context
) {

    fun setAlarm(alarmId: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java).putExtra("alarmId", alarmId)
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmId.toInt(), intent, PendingIntent.FLAG_CANCEL_CURRENT)

        //triggerTime(알람시간)은 alarmId(Date time)로 시간을 계산
        val calendar = Calendar.getInstance()
        calendar.time = Date(alarmId)
        calendar.set(Calendar.HOUR_OF_DAY, 11)
        calendar.set(Calendar.MINUTE, 30)
        val triggerTime = calendar.timeInMillis

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)

        //val textTriggerTime = (System.currentTimeMillis() + 30 * 1000)
        //alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, textTriggerTime, pendingIntent)
    }
}