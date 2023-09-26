package com.example.lokalko.ui.screens

import OAppBar
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.data.model.Category
import com.example.lokalko.data.model.City
import com.example.lokalko.data.model.Severity
import com.example.lokalko.ui.components.BottomBar
import com.example.lokalko.ui.components.DropDownMenuCategories
import com.example.lokalko.ui.components.DropDownMenuCities
import com.example.lokalko.ui.components.DropDownMenuSeverities
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.components.IssueCard
import com.example.lokalko.ui.components.LegendCard
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.MyRequestsScreenViewModel

object MyRequestsDestination : NavigationDestination {
    override val route = "my_requests"
    override val titleRes = R.string.my_requests
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRequestsScreen(
    navController: NavController,
    viewModel: MyRequestsScreenViewModel = hiltViewModel()
) {

    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val categoryOpt by viewModel.categoryOptions.collectAsState()
    val severityOpt by viewModel.severityOptions.collectAsState()
    val cityOpt by viewModel.cityOptions.collectAsState()
    val title = viewModel.title.collectAsState().value
    val description = viewModel.description.collectAsState().value
    val address = viewModel.address.collectAsState().value
    val selectedCity = viewModel.selectedCity.collectAsState().value
    val selectedCategory = viewModel.selectedCategory.collectAsState().value
    val selectedSeverity = viewModel.selectedSeverity.collectAsState().value
    val latitude = viewModel.latitude.collectAsState().value
    val longitude = viewModel.longitude.collectAsState().value
    val maxChar35 = 35
    val maxChar70 = 70

    Scaffold(
        topBar = {
            OAppBar(title = "My reports")
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            MyRequests(navController = navController, viewModel = viewModel)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value = true },
                backgroundColor = Color(244, 162, 97, 255),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_add_24),
                    contentDescription = "Add",
                    tint = Color(249, 244, 244, 255),
                )
            }
        }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            containerColor = Color(249, 244, 244, 255),
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Report new issue",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = Poppins,
                            color = Color(65, 65, 65, 255),
                        ),
                    )
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = viewModel::setTitle,
                        label = {
                            Text(
                                "Title",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins),
                        trailingIcon = {
                            Text(
                                text = "${maxChar35 - title.length}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color.Gray
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = viewModel::setDescription,
                        label = {
                            Text(
                                "Description",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins),
                        trailingIcon = {
                            Text(
                                text = "${maxChar70 - description.length}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color.Gray
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = address,
                        onValueChange = viewModel::setAddress,
                        label = {
                            Text(
                                "Address",
                                fontFamily = Poppins,
                                color = Color(65, 65, 65, 255)
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color(249, 244, 244, 255),
                            focusedBorderColor = Color(65, 65, 65, 255),
                            unfocusedBorderColor = Color(65, 65, 65, 255),
                            focusedLabelColor = Color(65, 65, 65, 255),
                            unfocusedLabelColor = Color(65, 65, 65, 255),
                            cursorColor = Color(65, 65, 65, 255),
                            textColor = Color(65, 65, 65, 255),
                        ),
                        textStyle = TextStyle.Default.copy(fontFamily = Poppins),
                        trailingIcon = {
                            Text(
                                text = "${maxChar35 - address.length}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color.Gray
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDownMenuCities(
                        label = "City",
                        cityOptions = cityOpt ?: emptyList(),
                        onCitySelected = viewModel::onSelectCity
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDownMenuCategories(
                        label = "Category",
                        categoryOptions = categoryOpt ?: emptyList(),
                        onCategorySelected = viewModel::onSelectCategory
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDownMenuSeverities(
                        label = "Severity",
                        severityOptions = severityOpt ?: emptyList(),
                        onSeveritySelected = viewModel::onSelectSeverity
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            navController.navigate(
                                route = "select_location_map"
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(249, 244, 244, 255),
                            containerColor = Color(244, 162, 97, 255)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Choose on a map",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                                color = Color(249, 244, 244, 255),
                            )
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isEmpty() || description.isEmpty() || address.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please fill in all fields!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.postRequest(
                                title,
                                description,
                                address,
                                selectedCity!!.city_id,
                                selectedCategory!!.category_id,
                                selectedSeverity!!.severity_id,
                                latitude,
                                longitude
                            )
                        }
                        Toast.makeText(
                            context,
                            "Reported successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                        showDialog.value = false
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(249, 244, 244, 255),
                        containerColor = Color(244, 162, 97, 255)
                    ),
                    modifier = Modifier.width(132.dp)
                ) {
                    Text(
                        text = "REPORT",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            color = Color(249, 244, 244, 255),
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(249, 244, 244, 255),
                        containerColor = Color(244, 162, 97, 255)
                    ),
                    modifier = Modifier.width(131.dp)
                ) {
                    Text(
                        text = "CANCEL",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            color = Color(249, 244, 244, 255),
                        )
                    )
                }
            }
        )
    }
}

@Composable
fun MyRequests(navController: NavController, viewModel: MyRequestsScreenViewModel) {

    val requests by viewModel.requests.collectAsState()
    val errorMessage = viewModel.errorMessage.value

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
                .padding(start = 16.dp, end = 16.dp, bottom = 86.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "This list contains all issues you reported.",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Center,
                        ),
                        color = Color(146, 167, 165, 255)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                LegendCard()
                Spacer(modifier = Modifier.height(32.dp))

                if (errorMessage != "No Error") {
                    ErrorMessage(errorMessage)
                }

                if (requests.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "You have not reported any issues yet.",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = Poppins,
                                textAlign = TextAlign.Center,
                            ),
                            color = Color(65, 65, 65, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            requests?.let { requestList ->
                items(requestList.size) { index ->
                    val request = requestList[index]

                    IssueCard(request.title, request.date, request.status) {
                        val id = request.request_id
                        navController.navigate(RequestDetailsDestination.route + "/$id")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

