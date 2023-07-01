package com.example.lokalko.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lokalko.R
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.viewModels.RequestDetailsScreenViewModel

object RequestDetailsDestination : NavigationDestination {
    override val route = "request_details"
    override val titleRes = R.string.request_details
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestDetailsScreen(
    navigateBack: () -> Unit,
    viewModel: RequestDetailsScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = RequestDetailsDestination.titleRes),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                },
                backgroundColor = Color(67, 160, 71, 255),
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            RequestDetails(viewModel = viewModel)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RequestDetails(viewModel: RequestDetailsScreenViewModel) {

    val request by viewModel.request.collectAsState()
    val errorMessage = viewModel.errorMessage.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (errorMessage != "No Error") {
            ErrorMessage(errorMessage)
        } else {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Title:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.title.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Description:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.description.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Date reported:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.date.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Time reported:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.time.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Address:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.address.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "City:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.city.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Category:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.category.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Severity:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.severity.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Service:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            if (request?.service?.isNotEmpty() == true) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = request!!.service,
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            } else {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Not Assigned",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Status:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.status.orEmpty(),
                style = TextStyle(fontSize = 16.sp)
            )
            if (request?.image?.isNotEmpty() == true) {
                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = { /* TODO */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (Color(
                            139,
                            146,
                            190,
                            255
                        ))
                    ),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(
                        text = "SEE IMAGE",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            } else {
                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = { /* TODO */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (Color(
                            139,
                            146,
                            190,
                            255
                        ))
                    ),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(
                        text = "NO IMAGE",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}