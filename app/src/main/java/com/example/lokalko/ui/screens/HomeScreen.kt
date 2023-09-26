package com.example.lokalko.ui.screens

import AppBar
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.components.BottomBar
import com.example.lokalko.ui.components.getRandomQuote
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.HomeScreenViewModel

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name2
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val totalRequests by viewModel.totalRequests.collectAsState()
    val reportedIssues by viewModel.reportedIssues.collectAsState()
    val resolvedIssues by viewModel.resolvedIssues.collectAsState()
    val user = viewModel.user.value
    val firstName = user?.first_name ?: ""

    Scaffold(
        topBar = {
            AppBar(userName = firstName, navController = navController) // TODO: staviti ime logovanog korisnika
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            Home(totalRequests = totalRequests, reportedIssues = reportedIssues, resolvedIssues = resolvedIssues)
        }
    )
}

@Composable
fun Home(totalRequests: Int?, reportedIssues: Int?, resolvedIssues: Int?) {

    val positiveImpactMessages = listOf(
        "You've helped your community ${totalRequests ?: 0} times so far. Thank you for your contributions!",
        "Your efforts have positively impacted the community ${totalRequests ?: 0} times so far!",
        "So far, you've made ${totalRequests ?: 0} positive contributions to the community. Great job!",
        "Thank you for being a part of ${totalRequests ?: 0} positive changes in the community so far!",
        "Your contributions have made a difference ${totalRequests ?: 0} times in the community so far!",
        "With ${totalRequests ?: 0} acts of kindness, you've made a positive impact on the community so far!",
        "You've been a force for good, helping the community ${totalRequests ?: 0} times so far!",
        "Thanks to you, the community has seen ${totalRequests ?: 0} positive actions so far!",
        "Your involvement has led to ${totalRequests ?: 0} positive changes in the community so far!",
        "You've made ${totalRequests ?: 0} contributions to the community so far. Keep up the great work!",
        "The community is better off thanks to your ${totalRequests ?: 0} efforts so far!",
        "So far, you've made ${totalRequests ?: 0} selfless acts to help others in the community!",
        "With ${totalRequests ?: 0} contributions, you've made a lasting impact on the community so far!",
        "Your ${totalRequests ?: 0} actions have brought positive change to the community so far!",
        "The community is grateful for your ${totalRequests ?: 0} contributions so far!",
        "You've made a positive difference ${totalRequests ?: 0} times in the community so far!",
        "With ${totalRequests ?: 0} acts of kindness, you've brightened the community so far!",
        "Your ${totalRequests ?: 0} efforts have helped the community thrive so far!",
        "Thank you for your dedication to making ${totalRequests ?: 0} positive changes in the community so far!",
        "You've made ${totalRequests ?: 0} valuable contributions to the community so far. Keep it up!",
        "Your kindness has shone through ${totalRequests ?: 0} times so far, making the community a better place!",
        "So far, your positive actions have made ${totalRequests ?: 0} meaningful impacts on the community!",
        "With ${totalRequests ?: 0} instances of help, you've been a beacon of hope for the community so far!",
        "Your contributions have been invaluable ${totalRequests ?: 0} times so far, creating positive change!",
        "Thanks to you, the community has experienced ${totalRequests ?: 0} moments of positivity so far!",
        "So far, you've demonstrated ${totalRequests ?: 0} acts of kindness that have left a lasting mark on the community!",
        "You've been a source of inspiration for the community ${totalRequests ?: 0} times so far, driving positive change!",
        "With ${totalRequests ?: 0} selfless gestures, you've been a true community leader so far!",
        "Your efforts have made a significant difference ${totalRequests ?: 0} times so far, shaping a better future for the community!",
        "So far, you've been a driving force for ${totalRequests ?: 0} positive transformations within the community!"
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_white),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 22.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            if (totalRequests == 0) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .height(135.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rect_home),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "You haven't reported any issues yet. Let's make a positive impact on our community!",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Poppins,
                                    textAlign = TextAlign.Center,
                                ),
                                color = Color(246, 249, 244, 255),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            } else {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .height(135.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rect_home),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = positiveImpactMessages.random(),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Poppins,
                                    textAlign = TextAlign.Center,
                                ),
                                color = Color(246, 249, 244, 255),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(210.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.reported_issues),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 48.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Text(
                                    text = "${reportedIssues ?: 0}",
                                    color = Color(246, 249, 244, 255),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    fontFamily = FontFamily.Default
                                )
                            }
                        }

                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .width(1.dp)
                                .fillMaxHeight()
                        )

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(210.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.resolved_issues),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 48.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Text(
                                    text = "${resolvedIssues ?: 0}",
                                    color = Color(246, 249, 244, 255),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    fontFamily = FontFamily.Default
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(200.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(249, 244, 244, 255),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Inspiring Quote",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Poppins,
                                textAlign = TextAlign.Center,
                            ),
                            color = Color(65, 65, 65, 255),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        val inspirationalQuote = getRandomQuote()
                        Text(
                            text = inspirationalQuote.quote,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                fontFamily = Poppins,
                                fontStyle = FontStyle.Italic,
                                color = Color(146, 167, 165, 255)
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = inspirationalQuote.author,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = Poppins,
                                color = Color(146, 167, 165, 255)
                            )
                        )
                    }
                }
            }
        }
    }
}