package com.ddd4.dropit.presentation.util.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.usecase.GetAlarmIdUseCase
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    private lateinit var notificationManager: NotificationManager
    private var alarmId: Long = 0

//    @Inject
//    lateinit var getAlarmIdUseCase: GetAlarmIdUseCase

//    private val serviceJob = SupervisorJob()

    override fun onReceive(context: Context, intent: Intent) {
//        Timber.d("Received intent : $intent")

//        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
//            //기기 재부팅 후 알람 등록
//            CoroutineScope(serviceJob).launch {
//                when (val result = getAlarmIdUseCase()) {
//                    is Result.Success -> {
//                        Timber.d("alarm service success")
//                        for (i in result.data.indices) {
//                            setAlarm(context, result.data[i].alarmId)
//                        }
//                    }
//                    is Result.Error -> {
//                        Timber.d("alarm service error")
//                        if (!serviceJob.isCancelled) {
//                            serviceJob.cancel()
//                        }
//                    }
//                }
//            }
//        }

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val extra = intent.extras
        alarmId = extra!!["alarmId"] as Long

        createNotificationChannel()
        deliverNotification(context, alarmId)
    }

    private fun deliverNotification(context: Context, alarmId: Long) {
        val contentIntent = Intent(context, MainActivity::class.java).putExtra("alarmId", alarmId)
        val contentPendingIntent = PendingIntent.getActivity(
            context, alarmId.toInt(), contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context,
            PRIMARY_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_add_white_24dp)
            .setContentTitle("가따버려 할 때야!")
            .setContentText("유통기한이 지난 내용이 있어요! 확인 후 버려주세요 :)")
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)

        notificationManager.notify(alarmId.toInt(), builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "drop item alarm notification",
                NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "AlarmManager Tests"
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}