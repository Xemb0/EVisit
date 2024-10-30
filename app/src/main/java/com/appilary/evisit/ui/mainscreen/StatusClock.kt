package com.appilary.evisit.ui.mainscreen

import androidx.compose.runtime.Composable

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appilary.evisit.R
import com.volt.greenvolt.theme.body
import com.volt.greenvolt.theme.element
import com.volt.greenvolt.theme.lightSecondaryGradient
import com.volt.greenvolt.theme.myAscent
import com.volt.greenvolt.theme.myText
import com.volt.greenvolt.theme.mytertiary
import com.volt.greenvolt.theme.titleMedium
import kotlinx.coroutines.delay
@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StatusClock() {
    // Animate the glow scale effect
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by infiniteTransition.animateFloat(
        initialValue = .80f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    var dayStarted by remember { mutableStateOf(true) }
    var dummyTravel by remember { mutableStateOf(0.0f) } // Distance traveled
    var shopsVisited by remember { mutableStateOf(0) } // Shops visited counter
    var timeRemaining by remember { mutableStateOf("08:00") } // Placeholder for time remaining

    val maxDistance = 1f

    // Simulate distance updates
    LaunchedEffect(Unit) {
        while (true) {
            delay(10000) // Update every 10 seconds
            dummyTravel += 0.009f // Simulate distance increment
            if (dummyTravel > maxDistance) dummyTravel = maxDistance
        }
    }

    // Calculate animated progress
    val animatedProgress by animateFloatAsState(
        targetValue = dummyTravel / maxDistance,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    val totalTravel = String.format("%.2f km", dummyTravel) // Display distance

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(300.dp)
            .padding(6.dp)
    ) {
        // Outer circular card
        Card(
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(containerColor = mytertiary.copy(alpha = .5f)),
            elevation = CardDefaults.cardElevation(100.dp),
            modifier = Modifier.size(300.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                if (dayStarted) {
                    CircularProgressIndicator(
                        progress = animatedProgress,
                        strokeWidth = 16.dp,
                        color = myAscent,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )

                    Card(
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.size(250.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Distance traveled display
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_logo_evisit),
                                    contentDescription = "User Icon",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(20.dp)
                                )
                                Text(
                                    text = totalTravel,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Shops visited display
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Shops: $shopsVisited / 4",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Time remaining display
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_users),
                                    contentDescription = "Clock",
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(end = 4.dp)
                                )
                                Text(text = "Time Left: $timeRemaining", fontSize = 14.sp, color = Color.Gray)
                            }
                        }
                    }
                } else {
                    Canvas(
                        modifier = Modifier
                            .size(300.dp)
                            .scale(scale)
                    ) {
                        drawCircle(
                            color = myAscent.copy(alpha = 0.3f),
                            radius = size.minDimension / 2
                        )
                    }
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_lense),
                            contentDescription = "User Icon",
                            modifier = Modifier.size(150.dp)
                        )
                    }
                }
            }
        }
    }
}
