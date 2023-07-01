package com.example.lokalko.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lokalko.ui.screens.HomeDestination

@Composable
fun BottomBar(navController: NavController) {

    val showDialog = remember { mutableStateOf(false) }
    val bluish = Color(4, 53, 85, 255)

    BottomNavigation(backgroundColor = Color(67, 160, 71, 255)) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            selected = navController.currentDestination?.route == HomeDestination.route,
            onClick = { navController.navigate(HomeDestination.route) },
            selectedContentColor = Color(255, 179, 0, 255),
            unselectedContentColor = Color.White,
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
            selected = navController.currentDestination?.route == "my_requests",
            onClick = { navController.navigate("my_requests") },
            selectedContentColor = Color(255, 179, 0, 255),
            unselectedContentColor = Color.White,
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            selected = navController.currentDestination?.route == "all_requests",
            onClick = { navController.navigate("all_requests") },
            selectedContentColor = Color(255, 179, 0, 255),
            unselectedContentColor = Color.White,
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            selected = navController.currentDestination?.route == "profile",
            onClick = { navController.navigate("profile") },
            selectedContentColor = Color(255, 179, 0, 255),
            unselectedContentColor = Color.White,
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) },
            selected = navController.currentDestination?.route == "logout",
            onClick = { showDialog.value = true },
            selectedContentColor = Color(255, 179, 0, 255),
            unselectedContentColor = Color.White,
        )
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "LOGOUT?",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Are you sure you want to logout?",
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            },
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
            confirmButton = {
                Button(
                    onClick = { navController.navigate("login") },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = bluish,
                        contentColor = Color.White
                    )
                ) {
                    Text(
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
                        containerColor = bluish,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(end = 49.dp)
                ) {
                    Text(
                        text = "CANCEL",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        )
    }
}