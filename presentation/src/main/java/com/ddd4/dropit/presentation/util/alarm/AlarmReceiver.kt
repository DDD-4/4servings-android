package com.ddd4.dropit.presentation.util.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.ui.main.MainActivity
import timber.log.Timber

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    private lateinit var notificationManager: NotificationManager
    private var alarmId: Int = 0

    override fun onReceive(context: Context, intent: Intent) {

        val extras = intent.extras
        if (extras != null) {
            alarmId = extras["alarmId"].toString().toInt()
        }
        Timber.d("alarmId: $alarmId")

//        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
//
//        }

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        deliverNotification(context, alarmId)
    }

    private fun deliverNotification(context: Context, alarmId: Int) {
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            context, alarmId, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

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

        notificationManager.notify(alarmId, builder.build())
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