package com.appilary.evisit

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed_apps.location_tracker.LocationManager
import com.appilary.evisit.database.MainViewModel
import com.appilary.evisit.theme.MyAppThemeComposable
import com.appilary.evisit.ui.HomePage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val locationManager by lazy {
        LocationManager(applicationContext)
    }

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.POST_NOTIFICATIONS,
)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this, permissions, 100
        )

        setContent {

            MyAppThemeComposable {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavLocationScreen
                    ) {


                        composable<HomePage> {HomePage(
//                                onSignUpClick = { userData ->
//                                    navController.navigate(
//                                        NavScreenCreateRoom(
//                                            userId = userData.userId?:"",
//                                            username = userData.username,
//                                            profilePictureUrl = userData.profilePictureUrl
//                                        )
//                                    )
//                                }
                            )
                        }
                        composable<NavLocationScreen> {LocationScreen(locationManager,applicationContext)
                        }

                }
            }
        }
    }







    }

}



//@Serializable
//data class HomePage(
//    val userId: String,
//    val username: String?,
//    val profilePictureUrl: String?
//)

@Serializable
data object HomePage

@Serializable
data object NavLocationScreen