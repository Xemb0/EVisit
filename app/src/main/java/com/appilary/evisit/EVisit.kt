package com.appilary.evisit

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.appilary.evisit.location.LocationTrackerService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EVisit : Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            LocationTrackerService.LOCATION_CHANNEL,
            "Location",
            NotificationManager.IMPORTANCE_LOW
        )

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}