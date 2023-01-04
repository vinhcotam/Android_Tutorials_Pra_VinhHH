package com.sun.android.ex_slide20

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.sun.android.R
import com.sun.android.databinding.ActivityNotificationBinding


class NotificationActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNotificationBinding.inflate(layoutInflater)
    }
    private var mNotifyManager: NotificationManager? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createNotificationChannel()
        binding.buttonNotifyMe.setOnClickListener{
            sendNotification()
        }
        binding.buttonUpdateMe.setOnClickListener{
            updateNotification()
        }
        binding.buttonCancelMe.setOnClickListener{
            cancelNotification()
        }
    }

    private fun sendNotification() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notifyBuilder = getNotificationBuilder()
        mNotifyManager?.notify(NOTIFICATION_ID, notifyBuilder.build())
    }

    private fun updateNotification() {
        val androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.ic_android)
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle(NOTIFICATION_UPDATE)
        )
        mNotifyManager?.notify(NOTIFICATION_ID, notifyBuilder.build())
    }

    private fun cancelNotification() {
        mNotifyManager?.cancel(NOTIFICATION_ID);
    }

    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                MASCOT_NOTIFICATION, NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = NOTIFICATION_DESCRIPTION
            mNotifyManager?.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, NotificationActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle(NOTIFICATION_CONTENT_TITLE)
            .setContentText(NOTIFICATION_CONTENT_TEXT)
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
    }

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
        private const val NOTIFICATION_DESCRIPTION = "Notification from Mascot"
        private const val NOTIFICATION_CONTENT_TEXT = "This is your notification text."
        private const val NOTIFICATION_CONTENT_TITLE = "You've been notified!"
        private const val MASCOT_NOTIFICATION = "Mascot Notification"
        private const val NOTIFICATION_UPDATE = "Notification Updated!"
    }
}
