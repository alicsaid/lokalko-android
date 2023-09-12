package com.example.lokalko.ui.screens

import AppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val totalRequests by viewModel.totalRequests.collectAsState()

    Scaffold(
        topBar = {
            AppBar(userName = "Said", avatarChoice= 10, navController = navController)
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            Home(totalRequests = totalRequests)
        }
    )
}

@Composable
fun Home(totalRequests: Int?) {
    val cardColor = Color(191, 250, 0)

    val positiveImpactMessages = listOf(
        "You've helped your community ${totalRequests ?: 0} time this year. Thank you for your contributions!",
        "Your efforts have positively impacted the community ${totalRequests ?: 0} times this year!",
        "This year, you've made ${totalRequests ?: 0} positive contributions to the community. Great job!",
        "Thank you for being a part of ${totalRequests ?: 0} positive changes in the community this year!",
        "Your contributions have made a difference ${totalRequests ?: 0} times in the community this year!",
        "With ${totalRequests ?: 0} acts of kindness, you've made a positive impact on the community this year!",
        "You've been a force for good, helping the community ${totalRequests ?: 0} times this year!",
        "Thanks to you, the community has seen ${totalRequests ?: 0} positive actions this year!",
        "Your involvement has led to ${totalRequests ?: 0} positive changes in the community this year!",
        "You've made ${totalRequests ?: 0} contributions to the community this year. Keep up the great work!",
        "The community is better off thanks to your ${totalRequests ?: 0} efforts this year!",
        "This year, you've made ${totalRequests ?: 0} selfless acts to help others in the community!",
        "With ${totalRequests ?: 0} contributions, you've made a lasting impact on the community this year!",
        "Your ${totalRequests ?: 0} actions have brought positive change to the community this year!",
        "The community is grateful for your ${totalRequests ?: 0} contributions this year!",
        "You've made a positive difference ${totalRequests ?: 0} times in the community this year!",
        "With ${totalRequests ?: 0} acts of kindness, you've brightened the community this year!",
        "Your ${totalRequests ?: 0} efforts have helped the community thrive this year!",
        "Thank you for your dedication to making ${totalRequests ?: 0} positive changes in the community!",
        "You've made ${totalRequests ?: 0} valuable contributions to the community this year. Keep it up!",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (totalRequests == 0) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = cardColor,
                        contentColor = Color.Black
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "You haven't reported any issues yet. Let's make a positive impact on our community!",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Poppins,
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            } else {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = cardColor,
                        contentColor = Color.Black
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = positiveImpactMessages.random(),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Poppins,
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(5) { index ->
                        Card(
                            modifier = Modifier
                                .width(150.dp)
                                .padding(horizontal = 16.dp)
                                .height(240.dp),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color(33, 37, 42)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.circuit_7955446_1280),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(160.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(33, 37, 42),
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
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = getRandomQuote(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                fontFamily = Poppins,
                                fontStyle = FontStyle.Italic,
                                color = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    }
}