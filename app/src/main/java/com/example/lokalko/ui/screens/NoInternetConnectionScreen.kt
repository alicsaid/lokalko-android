package com.example.lokalko.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.os.Process
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.stringResource
import com.example.lokalko.R
import com.example.lokalko.ui.components.NoInternetConnectionGif
import com.example.lokalko.ui.navigation.NavigationDestination

object NoInternetDestination : NavigationDestination {
    override val route = "no_internet"
    override val titleRes = R.string.no_internet1
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoInternetConnectionScreen() {
    Scaffold(
        content = {
            NoInternetConnection()
        }
    )
}

@Composable
fun NoInternetConnection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoInternetConnectionGif()

        Text(
            text = stringResource(R.string.no_internet1),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = stringResource(R.string.no_internet2),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = { exitApp() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = (Color(139, 146, 190, 255)),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(0.5f),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = "EXIT",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

private fun exitApp() {
    Process.killProcess(Process.myPid()) // Exit the application
}