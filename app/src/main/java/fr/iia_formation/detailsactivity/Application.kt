package fr.iia_formation.detailsactivity

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat

class PlageApplication : Application() {
    val TAG = "PlageApplication"

    override fun onCreate() {
        super.onCreate()

        val notificationManager = getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(FetchDataChannel, "Récupération des datas",
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "Accès au server pour récupérer les données"
            notificationManager . createNotificationChannel (this)
        }
        NotificationManagerCompat.from(this).apply{
            createNotificationChannel(channel)
        }

    }

    companion object {
        val FetchDataChannel = "PlageApplication"
    }


}