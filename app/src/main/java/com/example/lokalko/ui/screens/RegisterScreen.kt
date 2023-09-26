package com.example.lokalko.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.data.helpers.UiEvent
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.RegisterScreenViewModel

object RegisterDestination : NavigationDestination {
    override val route = "register"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            Register(navController = navController, viewModel = viewModel)
        }
    )
}

@Composable
fun Register(navController: NavController, viewModel: RegisterScreenViewModel) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var passwordVisible2 by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, context.getString(uiEvent.messageId), Toast.LENGTH_LONG).show()
                }
                is UiEvent.Navigate -> {
                    navController.navigate(uiEvent.route.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_green),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Register new account",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(249, 244, 244, 255)
                ),
                modifier = Modifier.padding(top = 32.dp , bottom = 32.dp)
            )

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = viewModel.capitalizeText(it) },
                label = { Text("First Name", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
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
                onValueChange = { lastName = viewModel.capitalizeText(it) },
                label = { Text("Last Name", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
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
                onValueChange = { city = viewModel.capitalizeText(it) },
                label = { Text("City", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
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
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
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
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
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
                    val image = if (passwordVisible)
                        R.drawable.round_visibility_24
                    else R.drawable.round_visibility_off_24

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = image),
                            description,
                            tint = Color(155, 155, 155, 255)
                        )
                    }
                }
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password", fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
                visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
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
                textStyle = TextStyle.Default.copy(fontFamily = Poppins),
                trailingIcon = {
                    val image = if (passwordVisible2)
                        R.drawable.round_visibility_24
                    else R.drawable.round_visibility_off_24

                    val description = if (passwordVisible2) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                        Icon(
                            painter = painterResource(id = image),
                            description,
                            tint = Color(155, 155, 155, 255)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    viewModel.register(email, password, confirmPassword, city, firstName, lastName)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = (Color(244, 162, 97, 255))
                ),
                modifier = Modifier.fillMaxWidth(0.5f),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = "REGISTER",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(249, 244, 244, 255),
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "Already a member?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(146, 167, 165, 255)
                )
            )

            ClickableText(
                text = AnnotatedString(" LOGIN"),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = Color(217, 217, 217, 255)
                ),
                onClick = { navController.navigate("login") }
            )
        }
    }
}