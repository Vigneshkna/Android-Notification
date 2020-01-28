package com.vk.notify

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            addNotification();
        }
    }
    private fun addNotification() {
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat
            .Builder(this)
            .setSound(uri) //sound from ringtone manager
            .setSmallIcon(R.drawable.ic_notify) //set icon for notification
            .setContentTitle("Sample Notification") //set title of notification
            .setContentText("This is a Sample Notification") //this is notification message
            .setAutoCancel(false) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_HIGH) //set priority of notification
            .setColor(Color.rgb(155,27,48)) //set yout own preferred color for background
            .setTicker("Ticker")
            .setVibrate(longArrayOf(10,500))//vibration
            //.setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))

        val notificationIntent = Intent(this, NotificationView::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message")

        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)
        //Add as notification
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }
}