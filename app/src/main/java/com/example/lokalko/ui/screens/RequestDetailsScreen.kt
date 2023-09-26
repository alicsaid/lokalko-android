package com.example.lokalko.ui.screens

import RDAppBar
import android.os.Build
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.RequestDetailsScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object RequestDetailsDestination : NavigationDestination {
    override val route = "request_details"
    override val titleRes = R.string.request_details
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestDetailsScreen(
    navController: NavController,
    requestId: Int?,
    viewModel: RequestDetailsScreenViewModel = hiltViewModel()
) {

    if (requestId != null) {
        viewModel.getRequestDetails(requestId)
    }

    Scaffold(
        topBar = {
            RDAppBar(title = "Report Details", navController = navController)
        },
        content = {
            RequestDetails(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RequestDetails(navController: NavController, viewModel: RequestDetailsScreenViewModel) {

    val request = viewModel.request.value
    val errorMessage = viewModel.errorMessage.value

    val title = request?.title.orEmpty()
    val description = request?.description.orEmpty()
    val dateReported = request?.date.orEmpty()
    val timeReported = request?.time.orEmpty()
    val address = request?.address.orEmpty()
    val city = request?.city.orEmpty()
    val category = request?.category.orEmpty()
    val severity = request?.severity.orEmpty()
    val service = request?.service.orEmpty()
    val status = request?.status.orEmpty()

    Box(
        modifier = Modifier.fillMaxSize(),
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
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                if (errorMessage != "No Error") {
                    ErrorMessage(errorMessage)
                } else {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Title",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = description,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Description",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        maxLines = 3,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = dateReported,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Date reported",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = timeReported,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Time reported",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = address,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Address",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = city,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "City",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = category,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Category",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = severity,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Severity",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = service,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Service",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    OutlinedTextField(
                        value = status,
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Status",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val id = request?.request_id
                            navController.navigate(IssueMapDestination.route + "/$id")
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = (Color(244, 162, 97, 255))
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        Text(
                            text = "See on a map",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color(249, 244, 244, 255),
                        )
                    }
                }
            }
        }
    }
}
