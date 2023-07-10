package com.example.lokalko.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.example.lokalko.ui.components.ColorLegendCard
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.components.IssueCard
import com.example.lokalko.ui.components.IssueStatus
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.viewModels.AllRequestsScreenViewModel
import com.example.lokalko.ui.viewModels.LoginScreenViewModel
import com.example.lokalko.ui.viewModels.MyRequestsScreenViewModel

object AllRequestsDestination : NavigationDestination {
    override val route = "all_requests"
    override val titleRes = R.string.all_requests
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRequestsScreen(
    navController: NavController,
    viewModel: AllRequestsScreenViewModel = hiltViewModel(),
    viewModel2: LoginScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = AllRequestsDestination.titleRes),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                },
                backgroundColor = Color(67, 160, 71, 255)
            )
        },
        bottomBar = {
            BottomBar(navController = navController, viewModel = viewModel2 )
        },
        content = {
            AllRequests(navController = navController, viewModel = viewModel)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllRequests(navController: NavController, viewModel: AllRequestsScreenViewModel) {

    val requests by viewModel.requests.collectAsState()
    val errorMessage = viewModel.errorMessage.value
    val city = "Konjic"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp, bottom = 56.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "This list contains all issues reported for $city.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            ColorLegendCard()
            Spacer(modifier = Modifier.height(32.dp))

            if (errorMessage != "No Error") {
                ErrorMessage(errorMessage)
            }
        }
        requests?.let { requestList ->
            items(requestList.size) { index ->
                val request = requestList[index]

                IssueCard(request.title, request.date, request.status) {
                    navController.navigate("request_details")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}