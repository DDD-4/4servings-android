package com.ddd4.dropit.presentation.util.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@ActivityScoped
class AlarmRegistUtil @Inject constructor(
    @ActivityContext private val context: Context
) {

    fun setAlarm(alarmId: Long, alarmTime: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java).putExtra("alarmId", alarmId)
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance()
        calendar.time = Date(alarmTime)
        Timber.d("alarmTime: $alarmTime / ${Date(alarmTime)} / ${calendar.time}")
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 30)
        val triggerTime = calendar.timeInMillis

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)

//        val calendar = Calendar.getInstance()
//        calendar.time = Date()
//        calendar.set(Calendar.HOUR_OF_DAY, 22)
//        calendar.set(Calendar.MINUTE, 45)
//        val bootTime = calendar.timeInMillis
//        val secTime = (System.currentTimeMillis() + 10 * 1000)
//        //Time Set Test with "bootTime" & "secTime"
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, secTime, pendingIntent)
    }
}