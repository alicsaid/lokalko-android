package com.example.lokalko.ui.screens

import OAppBar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.components.BottomBar
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.LoginScreenViewModel
import com.example.lokalko.ui.viewModels.ProfileScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ProfileDestination : NavigationDestination {
    override val route = "profile"
    override val titleRes = R.string.profile
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    viewModel2: LoginScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            OAppBar(title = "Profile")
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            Profile(viewModel = viewModel, viewModel2 = viewModel2, navController = navController)
        }
    )
}

@Composable
fun Profile(
    viewModel: ProfileScreenViewModel,
    viewModel2: LoginScreenViewModel,
    navController: NavController
) {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val user = viewModel.user.value

    var firstName by remember { mutableStateOf(user?.first_name ?: "") }
    var lastName by remember { mutableStateOf(user?.last_name ?: "") }
    var city by remember { mutableStateOf(user?.city ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }

    // Remember the initial values of the input fields
    val initialFirstName = remember { user?.first_name ?: "" }
    val initialLastName = remember { user?.last_name ?: "" }
    val initialCity = remember { user?.city ?: "" }
    val initialEmail = remember { user?.email ?: "" }

    // Track whether changes have been made
    var changesMade by remember { mutableStateOf(false) }

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "You can modify your profile info here.",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),
                color = Color(146, 167, 165, 255)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color(244, 162, 97, 255), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(id = R.drawable.round_person_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        tint = Color(249, 244, 244, 255),
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        firstName = viewModel.capitalizeText(it)
                        changesMade =
                            (it != initialFirstName) // Compare the current value with the initial value
                    },
                    label = {
                        Text(
                            "First Name",
                            fontFamily = Poppins,
                            color = Color(65, 65, 65, 255)
                        )
                    },
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
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = viewModel.capitalizeText(it)
                        changesMade =
                            (it != initialLastName) // Compare the current value with the initial value
                    },
                    label = {
                        Text(
                            "Last Name",
                            fontFamily = Poppins,
                            color = Color(65, 65, 65, 255)
                        )
                    },
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
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = city,
                    onValueChange = {
                        city = viewModel.capitalizeText(it)
                        changesMade =
                            (it != initialCity) // Compare the current value with the initial value
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color(249, 244, 244, 255),
                        focusedBorderColor = Color(65, 65, 65, 255),
                        unfocusedBorderColor = Color(65, 65, 65, 255),
                        focusedLabelColor = Color(65, 65, 65, 255),
                        unfocusedLabelColor = Color(65, 65, 65, 255),
                        cursorColor = Color(65, 65, 65, 255),
                        textColor = Color(65, 65, 65, 255),
                    ),
                    label = { Text("City", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        changesMade =
                            (it != initialEmail) // Compare the current value with the initial value
                    },
                    label = { Text("Email", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
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
                    textStyle = TextStyle.Default.copy(fontFamily = Poppins)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        viewModel.updateUser(
                            firstName = firstName,
                            lastName = lastName,
                            city = city,
                            email = email
                        )
                        Toast.makeText(
                            context,
                            "Info updated!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (changesMade) Color(244, 162, 97, 255) else Color(
                            65,
                            65,
                            65,
                            255
                        ),
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f),
                    contentPadding = PaddingValues(12.dp),
                    // Enable or disable the button based on the `changesMade` variable
                    enabled = changesMade
                ) {
                    Text(
                        text = "SAVE",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = if (changesMade) Color(249, 244, 244, 255) else Color(
                            65,
                            65,
                            65,
                            255
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(41.dp))

                ClickableText(
                    text = AnnotatedString(" LOGOUT"),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color(146, 167, 165, 255)
                    ),
                    onClick = { showDialog.value = true }
                )
            }

            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    containerColor = Color(249, 244, 244, 255),
                    title = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            androidx.compose.material3.Text(
                                text = "LOGOUT?",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color(65, 65, 65, 255),
                            )
                        }
                    },
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            androidx.compose.material3.Text(
                                text = "Are you sure you want to logout?",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = Poppins,
                                    textAlign = TextAlign.Center
                                ),
                                color = Color(65, 65, 65, 255),
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                        }
                    },
                    modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel2.logout()
                                showDialog.value = false
                                Toast.makeText(
                                    context,
                                    "Bye. See you soon!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                scope.launch {
                                    delay(1500)
                                    navController.navigate("login")
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(249, 244, 244, 255),
                                containerColor = Color(244, 162, 97, 255)
                            )
                        ) {
                            androidx.compose.material3.Text(
                                text = "LOGOUT",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog.value = false },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(249, 244, 244, 255),
                                containerColor = Color(244, 162, 97, 255)
                            ),
                            modifier = Modifier.padding(end = 49.dp)
                        ) {
                            androidx.compose.material3.Text(
                                text = "CANCEL",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}
