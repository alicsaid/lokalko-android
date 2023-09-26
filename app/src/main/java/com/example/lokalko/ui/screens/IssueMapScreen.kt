package com.example.lokalko.ui.screens

import RDAppBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.viewModels.IssueMapScreenViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

object IssueMapDestination : NavigationDestination {
    override val route = "issue_map"
    override val titleRes = R.string.map
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueMapScreen(
    navController: NavController,
    viewModel: IssueMapScreenViewModel = hiltViewModel(),
    requestId: Int?
) {
    Scaffold(
        topBar = {
            RDAppBar(title = "See issue on a map", navController = navController)
        },
        content = {
            IssueMap(viewModel = viewModel, requestId = requestId)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IssueMap(
    viewModel: IssueMapScreenViewModel,
    requestId: Int?
) {
    if (requestId != null) {
        viewModel.getIssueInfo(requestId)
    }

    println(viewModel.issue.value?.latitude ?: "te")

    val DEFAULT_LATITUDE = 44.1642
    val DEFAULT_LONGITUDE = 17.7680

    val issueLat = viewModel.issue.value?.latitude ?: DEFAULT_LATITUDE
    val issueLong = viewModel.issue.value?.longitude ?: DEFAULT_LONGITUDE
    val issuePosition = LatLng(issueLat, issueLong)

    println(issuePosition)

    val context = LocalContext.current
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(issuePosition, 14f)
    }

    val markerState = rememberMarkerState(position = issuePosition)
    val icon = bitmapDescriptorFromVector(context, R.drawable.not_started_lokalko)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
    ) {

        Marker(
            state = markerState,
            title = viewModel.issue.value?.title,
            snippet = viewModel.issue.value?.description,
            icon = icon
        )
    }
}