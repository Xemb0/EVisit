package com.appilary.evisit.ui.mainscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appilary.evisit.R
import com.volt.greenvolt.theme.body
import com.volt.greenvolt.theme.lightSecondaryGradient
import com.volt.greenvolt.theme.myAscent
import com.volt.greenvolt.theme.myPrimium
import com.volt.greenvolt.theme.myText
import com.volt.greenvolt.theme.mytertiary
import com.volt.greenvolt.theme.titleMedium

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
val context = LocalContext.current
    val scrollState = rememberScrollState()
    val topBarColor = remember { mutableStateOf(Color.Transparent) }
    Scaffold(
        containerColor = mytertiary,
        topBar = {
            TopAppBar(
                title = { Text("Hi, Virat", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_filled),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 4.dp)
                            .size(32.dp)
                    )
                },
                actions = {

                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp) // Outer padding for the notification icon
                            .clip(RoundedCornerShape(24.dp))
                            .padding(8.dp) // Inner padding for the icon
                    ) {
//                        Image(
//                            painter = painterResource(R.drawable.ic_bell_gradient),
//                            contentDescription = "Notification Bell Icon",
//                            modifier = Modifier.size(22.dp), // Set the size of the icon
//                        )
                        Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = myAscent)) {
                            Text("Mark day-end")
                        }
                        Box(
                            modifier = Modifier
                                .offset(0.dp, (-3).dp) // Offset to position the dot at the top end
                                .size(14.dp) // Size of the notification dot
                                .background(Color.Red, shape = CircleShape)
                                .align(Alignment.TopEnd) // Position at the top end of the icon
                        ) {
                            Text(
                                text = "1",
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center) // Center the text inside the dot
                            )
                        }
                    }
                },
                // Change background color based on scroll state
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = topBarColor.value
                )
            )
        },

        ) {
            paddingValues ->
        HomeScreen(scrollState,paddingValues, onAnimationComplete = { isFlat ->
            if (isFlat) {
                topBarColor.value = myPrimium
            } else {
                topBarColor.value = Color.Transparent

            }
        }
        )}

}

@Composable
fun HomeScreen(scrollState: ScrollState, paddingValues: PaddingValues, onAnimationComplete:(Boolean) -> Unit) {

    var isDayStarted = true
    val maxRadius = 300.dp
    val minRadius = 0.dp

    val cornerRadius = if (scrollState.maxValue > 0) {
        maxRadius - (maxRadius * (scrollState.value.toFloat() / scrollState.maxValue * 1.4f).coerceIn(0f, 1f))
    } else {
        maxRadius // Fallback to the max radius if maxValue is zero
    }

    LaunchedEffect(cornerRadius) {
        Log.d("MiningSection", "Corner Radius: $cornerRadius, Scroll Value: ${scrollState.value}, Is Flat: ${cornerRadius <= minRadius}")
        onAnimationComplete(cornerRadius <= minRadius+20.dp )
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Background Content (e.g., Profile Header)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(bottomStart = cornerRadius, bottomEnd = cornerRadius))
                .background(lightSecondaryGradient)
        ) {
            Spacer(modifier = Modifier.height(68.dp))
            StatusClock()
            if(isDayStarted){
            TrackingBeatRefresh()
            }
            else{
                TrackingStartBanner()
            }
        }

        TimerSection()
//        InviteCard()
        ShopListSection()
    }

}

@Composable
fun ShopListSection() {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min), // Ensures all elements within the Row have the same height
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dp)
                        .padding(vertical = 8.dp)
                        .background(myAscent.copy(alpha = .7f))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Shopes",
                    color = myText,
                    style = titleMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start
                )
                // Vertical Divider with the same height as the Card containers
                Text(
                    text = "View All",
                    color = myAscent,
                    style = body,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ShopCard(
                title = "Greenvolt Weekly!",
                description = "NOT VISITED",
                phoneNumber = "9087635684",
                icon = painterResource(id = R.drawable.ic_shope) // Replace with your image resource
            )
            ShopCard(
                title = "Greenvolt Weekly!",
                description = "VISITED",
                phoneNumber = "9868756756",
                icon = painterResource(id = R.drawable.ic_shope) // Replace with your image resource
            )
            ShopCard(
                title = "Greenvolt Weekly!",
                description = "VISITED",
                phoneNumber = "9867875675",
                icon = painterResource(id = R.drawable.ic_shope) // Replace with your image resource
            )
        }
    }


