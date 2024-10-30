package com.appilary.evisit.ui.mainscreen

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.runtime.Composable
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

@Composable
fun MiningSection(scrollState: ScrollState,
                  paddingValues: PaddingValues,
                  onAnimationComplete: (Boolean) -> Unit // Add a callback parameter
) {
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
            Spacer(modifier = Modifier.height(48.dp))
            StatusClock()
            TrackingBeatRefresh()
        }

        TimerSection()
        InviteCard()
        GreenVoltCardsSection()
    }
}

@Composable
fun GreenVoltCardsSection() {
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

@Composable
fun TimerSection(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(16.dp))
    {
        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "Timer Icon",
            modifier = Modifier
                .size(25.dp)
                .padding(4.dp)
        )
        Text(text = "00:00:00", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TrackingStartBanner() {
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

    // Button with pulsating glow effect
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {


        // Main circular button
        Card(
            colors = CardDefaults.cardColors(containerColor = myAscent),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
             Text("Start your day ", color = Color.White)
            }
        }
    }
}
@Composable
fun TrackingBeatRefresh() {
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

    // Button with pulsating glow effect
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Glow effect layer
        Canvas(
            modifier = Modifier
                .size(80.dp)
                .scale(scale)
        ) {
            drawCircle(
                color = myAscent.copy(alpha = 0.3f),
                radius = size.minDimension / 2
            )
        }

        // Main circular button
        Card(
            colors = CardDefaults.cardColors(containerColor = myAscent),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = Modifier.size(60.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tracking_refresh),
                    contentDescription = "Mining Icon",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileIconWithStatus() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp) // Outer padding for the profile icon
            .size(60.dp) // Size for the larger circle
            .clip(CircleShape)
            .background(lightSecondaryGradient) // Background color for the larger circle
    ) {
        // Text in the center of the large circle
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)// Offsets the dot slightly outwards
                .size(16.dp) // Size of the status dot
                .background(Color.Green, shape = CircleShape)
        )
        Text(
            text = "S",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        // Smaller green status dot in the bottom-right corner
    }
}

@Composable
fun ShopCard(
    title: String,
    description: String,
    phoneNumber: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    val visited = false
    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Left image section
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Right text section
            Column(modifier = Modifier.weight(1f)) {

                // Title
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                    Column {


                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00838F)
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Row {

                            Text(
                                text = "Contact - ",
                                style = titleMedium,
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                            // Date
                            Text(
                                text = phoneNumber,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_map_direction),
                        contentDescription = "Arrow Icon",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(34.dp),
                    )

                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

                // Description
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
                    if(visited) {


                        Image(
                            painter = painterResource(id = R.drawable.ic_done),
                            contentDescription = "Arrow Icon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(34.dp),
                        )
                    }else{
                        Image(
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = "Arrow Icon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(34.dp)
                            ,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGreenvoltCard() {
    ShopCard(
        title = "Rajiv kirana",
        description = "NOT YET VISITED",
        phoneNumber = "8756756734",
        icon = painterResource(id = R.drawable.ic_shope) // Replace with your image resource
    )
}


@Composable
fun InviteCard() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = myText.copy(alpha = .8f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_users),
                contentDescription = "Group Icon",
                modifier = Modifier.size(25.dp),
                tint = mytertiary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Create a group with friends to increase performance by 10% for each member.",
                    fontSize = 14.sp,
                    color = mytertiary
                )
            Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Invite your friend now",
                    color = element,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp),
                tint = (mytertiary)
            )
        }
    }
}

