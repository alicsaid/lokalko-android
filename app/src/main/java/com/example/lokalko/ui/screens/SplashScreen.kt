package com.example.lokalko.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.LoginScreenViewModel

object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {

    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        content = {
            Login(navController = navController, viewModel = viewModel)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value = true },
                backgroundColor = Color(33, 37, 42)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_help_24),
                    contentDescription = "Help",
                    tint = Color.White
                )
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
                        text = "LOKALKO",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item {
                        Text(
                            text = "1. About the App",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        Text(
                            text = "The Lokalko app is designed to empower citizens to report issues, problems, and malfunctions in their city to the relevant authorities. Through the Lokalko app, you can easily and efficiently notify the authorities about local concerns that require attention.",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "Discover the opportunity to contribute to the betterment of your city by reporting issues directly from the app. Whether you spot a damaged sidewalk, malfunctioning streetlight, or other deficiencies, Lokalko enables you to actively engage and contribute to preserving the beauty and functionality of your environment.",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "Download the Lokalko app today and become an active community member who enhances the quality of life in your city!",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    item {
                        Text(
                            text = "2. How to Use",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        Text(
                            text = "Using Lokalko is easy and intuitive. Follow these simple steps to get started:",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "1. Download the Lokalko app from the App Store or Google Play Store and install it on your device.",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "2. Launch the app and create a free account by providing your email address and creating a password.",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "3. Start using Lokalko effortlessly to report issues, explore local businesses, and engage with your community!",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    item {
                        Text(
                            text = "3. Contact and Support",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "If you have any questions or need support, please contact us at:",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        )
                        Text(
                            text = "Email: support@lokalko.com",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Blue,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                        Text(
                            text = "Phone: +387 62 143 021",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Blue,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            },
            confirmButton = { },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(33, 37, 42)
                    ),
                    modifier = Modifier.width(132.dp)
                ) {
                    Text(
                        text = "CLOSE",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }
        )
    }
}

@Composable
fun Login(navController: NavController, viewModel: LoginScreenViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val errorMessage = viewModel.errorMessage.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.transparent_lokalko_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(width = 250.dp, height = 250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Report community issues easily!",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = Poppins,
            color = Color(179, 179, 179, 255),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(46.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { androidx.compose.material3.Text("Email", fontFamily = Poppins) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(33, 37, 42),
                focusedLabelColor = Color(33, 37, 42),
                cursorColor = Color(33, 37, 42)
            ),
            textStyle = TextStyle.Default.copy(fontFamily = Poppins)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { androidx.compose.material3.Text("Password", fontFamily = Poppins) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(33, 37, 42),
                focusedLabelColor = Color(33, 37, 42),
                cursorColor = Color(33, 37, 42)
            ),
            textStyle = TextStyle.Default.copy(fontFamily = Poppins),
            trailingIcon = {
                val image = if (passwordVisible)
                    R.drawable.round_visibility_24
                else R.drawable.round_visibility_off_24

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = image),
                        description,
                        tint = Color.Gray
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(36.dp))

        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(
                        context,
                        "Please enter a valid email address!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (password.length < 6) {
                    Toast.makeText(
                        context,
                        "Password must be at least 6 characters long!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.login(email, password) { loggedIn ->
                        if (loggedIn) {
                            navController.navigate("home")
                        } else {
                            Toast.makeText(context, "Login unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                if (errorMessage != "No Error") {
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(33, 37, 42)
            ),
            modifier = Modifier.fillMaxWidth(0.5f),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = "LOGIN",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                ),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(110.dp))

        Text(
            text = "Not a member?",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
            )
        )

        ClickableText(
            text = AnnotatedString(" REGISTER"),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = Color(33, 37, 42)
            ),
            onClick = { navController.navigate("register") }
        )
    }
}