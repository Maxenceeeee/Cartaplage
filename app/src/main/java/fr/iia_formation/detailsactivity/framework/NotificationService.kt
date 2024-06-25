package fr.iia_formation.detailsactivity.framework

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import fr.iia_formation.detailsactivity.PlageApplication
import fr.iia_formation.detailsactivity.R

class NotificationService : Service() {
    val TAG = "PlageNotificationService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, PlageApplication.FetchDataChannel)
            .setContentTitle("PlageApplication")
            .setContentText("PlageApplication is running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        manager.notify(1, notification)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}