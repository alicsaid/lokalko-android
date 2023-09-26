package com.example.lokalko.ui.screens

import RDAppBar
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lokalko.R
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.theme.Poppins
import com.example.lokalko.ui.viewModels.MapScreenViewModel
import com.example.lokalko.ui.viewModels.MyRequestsScreenViewModel
import com.example.lokalko.ui.viewModels.SelectLocationScreenViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

object SelectLocationMapDestination : NavigationDestination {
    override val route = "select_location_map"
    override val titleRes = R.string.select_location_map
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLocationMapScreen(
    navController: NavController,
    viewModel: SelectLocationScreenViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            RDAppBar(title = "Select location", navController = navController)
        },
        content = {
            SelectLocationMap(navController = navController, viewModel = viewModel)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectLocationMap(
    navController: NavController,
    viewModel: SelectLocationScreenViewModel
) {

    val DEFAULT_LATITUDE = 44.1642
    val DEFAULT_LONGITUDE = 17.7680
    val city = viewModel.city.value
    val cityLat = city?.latitude ?: DEFAULT_LATITUDE
    val cityLong = city?.longitude ?: DEFAULT_LONGITUDE
    val cityPosition = LatLng(cityLat, cityLong)

    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityPosition, 14f)
    }
    val context = LocalContext.current
    //val markerPositionState = rememberMarkerState(position = bosnia)
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
    if (city != null) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
        ) {
            val position = cityPosition
            val markerState = rememberMarkerState(position = position)
            val icon = bitmapDescriptorFromVector(context, R.drawable.not_started_lokalko)

            DisposableEffect(markerState.position) {
                selectedLocation = markerState.position
                onDispose { }
            }

            Marker(
                state = markerState,
                title = "Drop a marker",
                snippet = "Hold to move",
                draggable = true,
                icon = icon
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (selectedLocation != null) {
                    Log.d(
                        "SelectLocationMap",
                        "Selektovana lokacija: ${selectedLocation?.latitude}, ${selectedLocation?.longitude}"
                    )
                }
                navController.popBackStack()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(249, 244, 244, 255),
                containerColor = Color(244, 162, 97, 255)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(132.dp)
        ) {
            Text(
                text = "SAVE",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    color = Color(249, 244, 244, 255),
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}