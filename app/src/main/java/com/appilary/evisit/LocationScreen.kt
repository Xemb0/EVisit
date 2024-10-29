package com.appilary.evisit

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmed_apps.location_tracker.LocationManager
import com.appilary.evisit.location.LocationTrackerService

@Composable
fun LocationScreen(locationManager:LocationManager,applicationContext: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var locationText by remember {
            mutableStateOf("")
        }

        Text(text = locationText)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                locationManager.getLocation { latitude, longitude ->
                    locationText = "Location: ..$latitude / ..$longitude"
                }
            }
        ) {
            Text(text = "Get Location")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                Intent(
                    applicationContext, LocationTrackerService::class.java
                ).also {
                    it.action = LocationTrackerService.Action.START.name
                    applicationContext.startService(it)
                }
            }
        ) {
            Text(text = "Start Tracking")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Intent(
                    applicationContext, LocationTrackerService::class.java
                ).also {
                    it.action = LocationTrackerService.Action.STOP.name
                    applicationContext.startService(it)
                }
            }
        ) {
            Text(text = "Stop Tracking")
        }
    }
}

