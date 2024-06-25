package fr.iia_formation.detailsactivity.framework

import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import fr.iia_formation.detailsactivity.PlageApplication
import fr.iia_formation.detailsactivity.R

class AlarmReceiver : BroadcastReceiver() {
    val TAG = "PlageAlarmReceiver"
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d(TAG, "onReceive")

        val manager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context,
            PlageApplication.FetchDataChannel)
            .setContentTitle("PlageApplication")
            .setContentText("PlageApplication is running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        manager.notify(1, notification)


    }
}