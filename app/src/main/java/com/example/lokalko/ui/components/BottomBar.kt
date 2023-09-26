package com.example.lokalko.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.screens.HomeDestination

@Composable
fun BottomBar(navController: NavController) {

    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier
            .height(70.dp),
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_home_24),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = navController.currentDestination?.route == HomeDestination.route,
            onClick = { navController.navigate(HomeDestination.route) },
            selectedContentColor = Color(146, 167, 165, 255),
            unselectedContentColor = Color(217, 217, 217, 255),
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = navController.currentDestination?.route == "my_requests",
            onClick = { navController.navigate("my_requests") },
            selectedContentColor = Color(146, 167, 165, 255),
            unselectedContentColor = Color(217, 217, 217, 255),
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_dashboard_24),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = navController.currentDestination?.route == "all_requests",
            onClick = { navController.navigate("all_requests") },
            selectedContentColor = Color(146, 167, 165, 255),
            unselectedContentColor = Color(217, 217, 217, 255),
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_person_24),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = navController.currentDestination?.route == "profile",
            onClick = { navController.navigate("profile") },
            selectedContentColor = Color(146, 167, 165, 255),
            unselectedContentColor = Color(217, 217, 217, 255),
        )
    }
}
