package com.example.lokalko.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.data.model.PostRequest
import com.example.lokalko.ui.components.BottomBar
import com.example.lokalko.ui.components.ColorLegendCard
import com.example.lokalko.ui.components.DropDownMenu
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.components.IssueCard
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.viewModels.LoginScreenViewModel
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
    viewModel: MyRequestsScreenViewModel = hiltViewModel(),
    viewModel2: LoginScreenViewModel = hiltViewModel()
) {

    val showDialog = remember { mutableStateOf(false) }
    val bluish = Color(4, 53, 85, 255)

    val categoryOpt = listOf("Traffic", "Utilities", "Public lighting", "Greenery", "Water and sewage", "Crime", "Safety", "Transportation", "Waste management", "Other" )
    val cityOpt = listOf("Konjic", "Sarajevo", "Tuzla")
    val severityOpt = listOf("High", "Medium", "Low")

    //val categoryOpt by viewModel.categories.collectAsState()
    //val severityOpt by viewModel.severities.collectAsState()
    //val cityOpt by viewModel.cities.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city = 1
    var category = 2
    var severity = 3

    val maxChar35 = 35
    val maxChar70 = 70

    val selectedImages = remember { mutableStateListOf<Uri>() }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            if (data?.clipData != null) {
                // Ako je odabrano više slika
                val clipData = data.clipData
                val count = clipData?.itemCount ?: 0
                val maxImages = 3

                for (i in 0 until minOf(count, maxImages)) {
                    val uri = clipData?.getItemAt(i)?.uri
                    uri?.let { selectedImages.add(it) }
                }
            } else if (data?.data != null) {
                // Ako je odabrana samo jedna slika
                val uri = data.data
                uri?.let { selectedImages.add(it) }
            }

            // Ovdje možete obraditi listu odabranih slika
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                text = stringResource(id = MyRequestsDestination.titleRes),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )},
                backgroundColor = Color(67, 160, 71, 255)
            )
        },
        bottomBar = {
            BottomBar(navController = navController, viewModel = viewModel2)
        },
        content = {
            MyRequests(navController = navController, viewModel = viewModel)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value  = true },
                backgroundColor = Color(255, 179, 0, 255)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White)
            }
        }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Submit new request",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { if (it.length <= maxChar35) title = capitalizeText(it) },
                        label = { Text("Title") },
                        singleLine = true,
                        trailingIcon = {
                            Text(
                                text = "${maxChar35 - title.length}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = { if (it.length <= maxChar70) description = capitalizeText(it) },
                        label = { Text("Description") },
                        singleLine = true,
                        trailingIcon = {
                            Text(
                                text = "${maxChar70 - description.length}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = address,
                        onValueChange = { if (it.length <= maxChar35) address = capitalizeText(it) },
                        label = { Text("Address") },
                        singleLine = true,
                        trailingIcon = {
                            Text(
                                text = "${maxChar35 - address.length}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDownMenu(label = "City", options = cityOpt)
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDownMenu(label = "Category", options = categoryOpt)
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDownMenu(label = "Severity", options = severityOpt)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
                            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                            galleryIntent.type = "image/*"
                            galleryLauncher.launch(Intent.createChooser(galleryIntent, "Select Images"))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = bluish,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Select images",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                }
            },
            confirmButton = {
                Button(
                    onClick = {
//                        val requestData = PostRequest(
//                            title = title,
//                            description = description,
//                            address = address,
//                            city = city,
//                            category = category,
//                            severity = severity,
//                            images = selectedImages.toList()
//                        )
//                        viewModel.postRequest(requestData)
                        showDialog.value = false
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = bluish,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(132.dp)
                ) {
                    Text(text = "SEND",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
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
                        containerColor = bluish,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(131.dp)
                ) {
                    Text(text = "CANCEL",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
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
                    text = "This list contains all issues you reported.",
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

fun capitalizeText(text: String): String {
    return text.lowercase().replaceFirstChar { it.uppercase() }
}