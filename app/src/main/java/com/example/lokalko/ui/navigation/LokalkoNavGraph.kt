package com.example.lokalko.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lokalko.ui.screens.AllRequestsDestination
import com.example.lokalko.ui.screens.AllRequestsScreen
import com.example.lokalko.ui.screens.HomeDestination
import com.example.lokalko.ui.screens.HomeScreen
import com.example.lokalko.ui.screens.IssueMapDestination
import com.example.lokalko.ui.screens.IssueMapScreen
import com.example.lokalko.ui.screens.LoginDestination
import com.example.lokalko.ui.screens.LoginScreen
import com.example.lokalko.ui.screens.MapDestination
import com.example.lokalko.ui.screens.MapScreen
import com.example.lokalko.ui.screens.MyRequestsDestination
import com.example.lokalko.ui.screens.MyRequestsScreen
import com.example.lokalko.ui.screens.ProfileDestination
import com.example.lokalko.ui.screens.ProfileScreen
import com.example.lokalko.ui.screens.RegisterDestination
import com.example.lokalko.ui.screens.RegisterScreen
import com.example.lokalko.ui.screens.RequestDetailsDestination
import com.example.lokalko.ui.screens.RequestDetailsScreen
import com.example.lokalko.ui.screens.SelectLocationMapDestination
import com.example.lokalko.ui.screens.SelectLocationMapScreen
import com.example.lokalko.ui.screens.SplashDestination
import com.example.lokalko.ui.screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LokalkoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route,
        modifier = modifier
    ) {
        composable(route = SplashDestination.route) {
            SplashScreen(navController = navController)
        }
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
        composable(route = SelectLocationMapDestination.route) {
            SelectLocationMapScreen(navController = navController)
        }
        composable(route = AllRequestsDestination.route) {
            AllRequestsScreen(navController = navController)
        }
        composable(route = MapDestination.route) {
            MapScreen(navController = navController)
        }
        composable(
            RequestDetailsDestination.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            RequestDetailsScreen(
                navController = navController,
                requestId = backStackEntry.arguments?.getInt("id")
            )
        }
        composable(
            IssueMapDestination.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            IssueMapScreen(
                navController = navController,
                requestId = backStackEntry.arguments?.getInt("id")
            )
        }
        composable(route = ProfileDestination.route) {
            ProfileScreen(navController = navController)
        }
    }
}
