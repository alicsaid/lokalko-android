package com.example.lokalko.ui.screens

import RDAppBar
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lokalko.R
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.viewModels.MapScreenViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

object MapDestination : NavigationDestination {
    override val route = "map"
    override val titleRes = R.string.map
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            RDAppBar(title = "Map", navController = navController)
        },
        content = {
            Map(viewModel = viewModel)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Map(
    viewModel: MapScreenViewModel
) {
    val DEFAULT_LATITUDE = 44.1642
    val DEFAULT_LONGITUDE = 17.7680

    val reports by viewModel.reports.collectAsState()
    val city = viewModel.city.value
    val cityLat = city?.latitude ?: DEFAULT_LATITUDE
    val cityLong = city?.longitude ?: DEFAULT_LONGITUDE
    val cityPosition = LatLng(cityLat, cityLong)
    //println(cityPosition)


//    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityPosition, 14f)
    }

    if (city != null) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
        ) {
            for (report in reports!!) {
                val position = LatLng(report.latitude, report.longitude)
                MapMarker(
                    position = position,
                    title = report.title,
                    snippet = "Severity: ${report.severity}",
                    context = LocalContext.current,
                    status = when (report.status) {
                        "Not started" -> ReportStatus.NOT_STARTED
                        "Started" -> ReportStatus.STARTED
                        "Finished" -> ReportStatus.FINISHED
                        else -> ReportStatus.NOT_STARTED
                    }
                )
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    title: String,
    snippet: String,
    status: ReportStatus,
) {
    val iconResourceId = when (status) {
        ReportStatus.NOT_STARTED -> R.drawable.not_started_lokalko
        ReportStatus.STARTED -> R.drawable.started_lokalko
        ReportStatus.FINISHED -> R.drawable.finished_lokalko
    }

    val icon = bitmapDescriptorFromVector(context, iconResourceId)

    Marker(
        state = MarkerState(position = position),
        title = title,
        snippet = snippet,
        icon = icon
    )
}

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

enum class ReportStatus {
    NOT_STARTED,
    STARTED,
    FINISHED
}