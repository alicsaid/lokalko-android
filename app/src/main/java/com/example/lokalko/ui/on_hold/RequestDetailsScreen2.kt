package com.example.lokalko.ui.on_hold

import RDAppBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.RequestDetailsScreenViewModel

object RequestDetailsDestination : NavigationDestination {
    override val route = "request_details"

    //    private const val requestId = "request_id"
//    val routeWithArg = "${route}/${requestId}"
    override val titleRes = R.string.request_details
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestDetailsScreen(
    navController: NavController,
    viewModel: RequestDetailsScreenViewModel = hiltViewModel(),
    request_id: String
) {
    Scaffold(
        topBar = {
            RDAppBar(title = "Report Details", navController = navController)
        },
        content = {
            RequestDetails(viewModel = viewModel, request_id = request_id)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RequestDetails(viewModel: RequestDetailsScreenViewModel, request_id: String) {
    println("DAAAAAAAAAAAAAAA $request_id")
    val request = viewModel.request.value
    val errorMessage = viewModel.errorMessage.value
    var isImageModalOpen by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()

    val animals = listOf(
        R.drawable.lokalko,
        R.drawable.quizzy,
        R.drawable._01846,
    )

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
    val hasImages = request?.image?.isNotEmpty() == true

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
                    label = { Text(text = "Title", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { },
                    label = { Text(text = "Description", fontFamily = Poppins) },
                    readOnly = true,
                    maxLines = 3,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = dateReported,
                    onValueChange = { },
                    label = { Text(text = "Date reported", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = timeReported,
                    onValueChange = { },
                    label = { Text(text = "Time reported", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { },
                    label = { Text(text = "Address", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = city,
                    onValueChange = { },
                    label = { Text(text = "City", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = category,
                    onValueChange = { },
                    label = { Text(text = "Category", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = severity,
                    onValueChange = { },
                    label = { Text(text = "Severity", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = service,
                    onValueChange = { },
                    label = { Text(text = "Service", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = status,
                    onValueChange = { },
                    label = { Text(text = "Status", fontFamily = Poppins) },
                    readOnly = true,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(33, 37, 42),
                        focusedLabelColor = Color(33, 37, 42),
                        cursorColor = Color(33, 37, 42)
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                if (!hasImages) {
                    Button(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        onClick = { isImageModalOpen = true },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(33, 37, 42)
                        ),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        Text(
                            text = "SEE IMAGES",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        onClick = { /*isImageModalOpen = true*/ },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(33, 37, 42)
                        ),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        Text(
                            text = "NO IMAGES",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                }
            }
        }

        item {
            if (isImageModalOpen) {
                Dialog(
                    onDismissRequest = { isImageModalOpen = false },
                    properties = DialogProperties(dismissOnClickOutside = true)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(350.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HorizontalPager(
                            pageCount = animals.size,
                            state = pagerState,
                            key = { animals[it] },
                            pageSize = PageSize.Fill
                        ) { index ->
                            Image(
                                painter = painterResource(id = animals[index]),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
