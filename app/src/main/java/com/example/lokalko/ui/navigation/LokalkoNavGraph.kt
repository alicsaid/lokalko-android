package com.example.lokalko.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lokalko.ui.screens.AllRequestsDestination
import com.example.lokalko.ui.screens.AllRequestsScreen
import com.example.lokalko.ui.screens.HomeDestination
import com.example.lokalko.ui.screens.HomeScreen
import com.example.lokalko.ui.screens.LoginDestination
import com.example.lokalko.ui.screens.LoginScreen
import com.example.lokalko.ui.screens.MyRequestsDestination
import com.example.lokalko.ui.screens.MyRequestsScreen
import com.example.lokalko.ui.screens.NoInternetConnection
import com.example.lokalko.ui.screens.NoInternetConnectionScreen
import com.example.lokalko.ui.screens.NoInternetDestination
import com.example.lokalko.ui.screens.ProfileDestination
import com.example.lokalko.ui.screens.ProfileScreen
import com.example.lokalko.ui.screens.RegisterDestination
import com.example.lokalko.ui.screens.RegisterScreen
import com.example.lokalko.ui.screens.RequestDetailsDestination
import com.example.lokalko.ui.screens.RequestDetailsScreen
import com.example.lokalko.ui.screens.ServerErrorDestination
import com.example.lokalko.ui.screens.ServerErrorScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LokalkoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination.route,
        modifier = modifier
    ) {
        composable(route = LoginDestination.route) {
            LoginScreen(navController = navController)
        }
        composable(route = RegisterDestination.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = HomeDestination.route) {
            HomeScreen(navController = navController)
        }
        composable(route = MyRequestsDestination.route) {
            MyRequestsScreen(navController = navController)
        }
        composable(route = AllRequestsDestination.route) {
            AllRequestsScreen(navController = navController)
        }
        composable(route = RequestDetailsDestination.route) {
            RequestDetailsScreen(navigateBack = { navController.popBackStack() })
        }
        composable(route = ProfileDestination.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = NoInternetDestination.route) {
            NoInternetConnectionScreen()
        }
        composable(route = ServerErrorDestination.route) {
            ServerErrorScreen()
        }
    }
}
