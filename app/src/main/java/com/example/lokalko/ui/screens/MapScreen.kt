package com.example.lokalko.ui.screens

import OAppBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.screens.RequestDetailsDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.AllRequestsScreenViewModel

object AllRequestsDestination : NavigationDestination {
    override val route = "all_requests"
    override val titleRes = R.string.all_requests
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRequestsScreen(
    navController: NavController,
    viewModel: AllRequestsScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            OAppBar(title = "All reports")
        },
        bottomBar = {
            BottomBar(navController = navController)
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
            .padding(top = 36.dp, start = 16.dp, end = 16.dp, bottom = 86.dp)
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
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            ColorLegendCard()
            Spacer(modifier = Modifier.height(32.dp))

            if (errorMessage != "No Error") {
                ErrorMessage(errorMessage)
            }

            if (requests.isNullOrEmpty()) {
                Text(
                    text = "There is no any reported issues yet for $city.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Poppins,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        requests?.let { requestList ->
            items(requestList.size) { index ->
                val request = requestList[index]

                IssueCard(request.title, request.date, request.status) {
                    navController.navigate(RequestDetailsDestination.route + request.request_id)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}